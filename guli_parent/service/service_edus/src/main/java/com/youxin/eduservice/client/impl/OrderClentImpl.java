package com.youxin.eduservice.client.impl;

import com.youxin.eduservice.client.OrderClent;
import org.springframework.stereotype.Component;

@Component
public class OrderClentImpl implements OrderClent {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
