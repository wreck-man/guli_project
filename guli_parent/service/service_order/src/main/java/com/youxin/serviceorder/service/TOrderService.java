package com.youxin.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.serviceorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-29
 */
public interface TOrderService extends IService<TOrder> {

    String addOrder(String courseId, String token);

    Result OrderPage(Page page1, QueryWrapper wrapper);
}
