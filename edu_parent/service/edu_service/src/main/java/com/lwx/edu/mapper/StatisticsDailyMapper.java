package com.lwx.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.edu.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.vo.DailyVo;
import com.lwx.edu.entity.vo.MonthVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-17
 */
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

    List<DailyVo> getWeekData();

    List<MonthVo> getMonthData(@Param(Constants.WRAPPER)QueryWrapper wrapper);

    void updateRegister(String today);

    void updateLogin(String today);

    void updateCourse(String today);
}
