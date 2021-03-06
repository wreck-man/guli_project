package com.youxin.serviceorder.controller;


import com.youxin.commonutils.Result;
import com.youxin.serviceorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-29
 */

@RestController
@RequestMapping("/serviceorder/t-pay-log")
public class TPayLogController {
    @Autowired
    private TPayLogService payLogService;
    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo) {
        Map map = payLogService.createNative(orderNo);
        return Result.ok().data(map);
    }
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
//调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return Result.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
//更改订单状态
            payLogService.updateOrderStatus(map);
            return Result.ok().message("支付成功");
        }
        return Result.ok().code(25000).message("支付中");
    }



}

