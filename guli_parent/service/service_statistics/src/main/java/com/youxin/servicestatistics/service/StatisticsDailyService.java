package com.youxin.servicestatistics.service;

import com.youxin.servicestatistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-30
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDay(String date);
}
