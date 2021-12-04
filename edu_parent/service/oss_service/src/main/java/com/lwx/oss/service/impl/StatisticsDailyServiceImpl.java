package com.lwx.oss.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.oss.entity.StatisticsDaily;
import com.lwx.oss.mapper.StatisticsDailyMapper;
import com.lwx.oss.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {


    @Autowired
    private StatisticsDailyMapper dailyMapper;

    @Override
    public void updateVideo() {
        dailyMapper.updateVideo(DateUtil.today());
    }

}
