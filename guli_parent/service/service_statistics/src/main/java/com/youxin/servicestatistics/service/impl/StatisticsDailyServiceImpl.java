package com.youxin.servicestatistics.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.servicestatistics.clients.UctermenberClient;
import com.youxin.servicestatistics.entity.StatisticsDaily;
import com.youxin.servicestatistics.mapper.StatisticsDailyMapper;
import com.youxin.servicestatistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-30
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UctermenberClient uctermenberClient;
    @Override
    public void createStatisticsByDay(String date) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("date_calculated",date);
        baseMapper.delete(wrapper);
        Integer registerCount=Integer.valueOf(uctermenberClient.selectRegister(date));
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        StatisticsDaily daily=new StatisticsDaily();
        daily.setCourseNum(courseNum);
        daily.setLoginNum(loginNum);
        daily.setRegisterNum(registerCount);
        daily.setVideoViewNum(videoViewNum);
        daily.setDateCalculated(date);
        baseMapper.insert(daily);

    }
}
