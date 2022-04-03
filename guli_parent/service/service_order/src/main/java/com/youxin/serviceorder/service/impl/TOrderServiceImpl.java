package com.youxin.serviceorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.CourseWebVo;
import com.youxin.commonutils.Result;
import com.youxin.commonutils.UcenterMember;
import com.youxin.serviceorder.client.CourseClient;
import com.youxin.serviceorder.client.EduUcenterClent;
import com.youxin.serviceorder.entity.TOrder;
import com.youxin.serviceorder.mapper.TOrderMapper;
import com.youxin.serviceorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxin.serviceorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-29
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private CourseClient courseClient;
    @Autowired
    private EduUcenterClent eduUcenterClent;
    @Override
    public String addOrder(String courseId, String token) {
        CourseWebVo courseWebVo=courseClient.webCourseVo(courseId);
        UcenterMember ucenterMember=eduUcenterClent.getLoginInfo(token);
        TOrder order=new TOrder();
        order.setCourseId(courseId);
        order.setCourseCover(courseWebVo.getCover());
        order.setCourseTitle(courseWebVo.getTitle());
        order.setMemberId(ucenterMember.getId());
        order.setNickname(ucenterMember.getNickname());
        order.setPayType(1);
        order.setMobile(ucenterMember.getMobile());
        order.setTotalFee(courseWebVo.getPrice());
        order.setTeacherName(courseWebVo.getTeacherName());
        order.setStatus(0);
        order.setOrderNo(OrderNoUtil.getOrderNo());
        baseMapper.insert(order);
        return order.getOrderNo();
    }

    @Override
    public Result OrderPage(Page page1, QueryWrapper wrapper) {

        page1= (Page) baseMapper.selectPage(page1,wrapper);
        Long total=page1.getTotal();
        List<TOrder> list=page1.getRecords();
        return Result.ok().data("total",total).data("list",list);
    }
}
