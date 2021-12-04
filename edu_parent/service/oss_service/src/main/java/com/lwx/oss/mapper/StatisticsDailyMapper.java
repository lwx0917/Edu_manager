package com.lwx.oss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.oss.entity.StatisticsDaily;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

    void updateVideo(String today);
}
