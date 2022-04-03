package com.youxin.eduservice.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.eduservice.client.impl.OrderClentImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "service-order",fallback = OrderClentImpl.class)
public interface OrderClent {
    @GetMapping("/serviceorder/t-order/isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable(name = "memberid") String memberid,
                               @PathVariable(name = "id") String id) ;
}
