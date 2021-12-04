package com.lwx.edu.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lwx.edu.entity.StatisticsDaily;
import com.lwx.edu.entity.vo.DailyVo;
import com.lwx.edu.entity.vo.MonthVo;
import com.lwx.edu.mapper.StatisticsDailyMapper;
import com.lwx.edu.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @author lwx
 * @since 2021-11-17
 */
@Service
@Slf4j
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Resource
    private StatisticsDailyMapper dailyMapper;

    @Override
    public List<DailyVo> getWeekData() {
        return dailyMapper.getWeekData();
    }

    @Override
    public List<MonthVo> getMonthData(String year) {
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper.like("date_calculated", year);
        return dailyMapper.getMonthData(wrapper);
    }

    @Override
    public HashSet<String> getYear() {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.select("date_calculated");
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);
        HashSet<String> set = new HashSet<>();
        for (StatisticsDaily s : statisticsDailies) {
            set.add(s.getDateCalculated().substring(0, 4));
        }
        return set;
    }

    @Override
    public Integer isExist(String today) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", today);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public void create() {
        StatisticsDaily daily = new StatisticsDaily();
        daily.setDateCalculated(DateUtil.today());
        baseMapper.insert(daily);
    }

    @Override
    public void updateRegister() {
        dailyMapper.updateRegister(DateUtil.today());
    }

    @Override
    public void updateLogin() {
        dailyMapper.updateLogin(DateUtil.today());
    }

    @Override
    public void updateCourse() {
        dailyMapper.updateCourse(DateUtil.today());
    }
}
