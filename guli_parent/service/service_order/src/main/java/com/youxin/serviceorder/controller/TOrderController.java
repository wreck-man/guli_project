package com.youxin.serviceorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.commonutils.utils.JwtUtils;
import com.youxin.serviceorder.entity.TOrder;
import com.youxin.serviceorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-29
 */

@RestController
@RequestMapping("/serviceorder/t-order")
public class TOrderController {
    @Autowired
    private TOrderService tOrderService;
    @GetMapping("isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id) {
//订单状态是1表示支付成功
        int count = tOrderService.count(new QueryWrapper<TOrder>().eq("member_id", memberid).eq("course_id", id).eq("status", 1));
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/addOrder/{courseId}")
    public Result addOrder(@PathVariable String courseId, HttpServletRequest request){
        System.out.println("hello world");
        String token= JwtUtils.getMemberIdByJwtToken(request);
        String order_id=tOrderService.addOrder(courseId,token);
      return Result.ok().data("orderId",order_id);
    }
    @GetMapping("/getOrder/{orderId}")
    public Result get(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = tOrderService.getOne(wrapper);
        return Result.ok().data("item", order);
    }
    @PostMapping("/selectPages/{page}/{limit}")
    public Result getList(@PathVariable long page,@PathVariable long limit,@RequestBody(required = false) TOrder order){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("gmt_create");
        Page page1=new Page(page,limit);
        Result map=tOrderService.OrderPage(page1,wrapper);
        return map;
    }


}

