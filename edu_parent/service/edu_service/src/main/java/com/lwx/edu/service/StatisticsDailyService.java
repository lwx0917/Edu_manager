package com.lwx.edu.service;

import com.lwx.edu.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.DailyVo;
import com.lwx.edu.entity.vo.MonthVo;

import java.util.HashSet;
import java.util.List;

/**
 * @author lwx
 * @since 2021-11-17
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    List<DailyVo> getWeekData();

    List<MonthVo> getMonthData(String year);

    HashSet<String> getYear();

    Integer isExist(String today);

    void create();

    void updateRegister();

    void updateLogin();

    void updateCourse();
}
