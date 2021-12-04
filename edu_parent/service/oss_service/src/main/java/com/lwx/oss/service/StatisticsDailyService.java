package com.lwx.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.oss.entity.StatisticsDaily;
import org.springframework.stereotype.Component;

//@Component
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    void updateVideo();
}
