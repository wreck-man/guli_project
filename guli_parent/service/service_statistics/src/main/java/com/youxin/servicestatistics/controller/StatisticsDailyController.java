package com.youxin.servicestatistics.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.commonutils.Result;
import com.youxin.servicestatistics.clients.UctermenberClient;
import com.youxin.servicestatistics.entity.StatisticsDaily;
import com.youxin.servicestatistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-30
 */

@RestController
@RequestMapping("/servicestatistics/statistics-daily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService service;
    @GetMapping("/{date}")
    public Result DateCount(@PathVariable String date){
       service.createStatisticsByDay(date);
        return Result.ok();

    }
    @GetMapping("/{begin}/{end}/{type}")
    public Result CountResult(@PathVariable String begin ,@PathVariable String end ,@PathVariable String type ){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByAsc("date_calculated");
        wrapper.between("date_calculated",begin,end);
        wrapper.select(type,"date_calculated");
       List<StatisticsDaily> list= service.list(wrapper);
       List<String> l1=new ArrayList<>();
        List<Integer> l2=new ArrayList<>();
        for (StatisticsDaily daily:list){
            l1.add(daily.getDateCalculated());
            switch (type){
                case "register_num":l2.add(daily.getRegisterNum());break;
                case "login_num":l2.add(daily.getLoginNum());break;
                case "video_view_num":l2.add(daily.getVideoViewNum());break;
                case "course_num":l2.add(daily.getCourseNum());break;
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("xList",l1);
        map.put("yList",l2);
        return Result.ok().data("map",map);
    }

}

