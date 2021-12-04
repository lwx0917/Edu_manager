package com.lwx.edu.controller;


import com.lwx.edu.entity.vo.DailyVo;
import com.lwx.edu.entity.vo.MonthVo;
import com.lwx.edu.service.StatisticsDailyService;
import com.lwx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @author lwx
 * @since 2021-11-17
 */
@RestController
@RequestMapping("/edu/chart")
@CrossOrigin
@Slf4j
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService service;

    @GetMapping("/getWeekData")
    public Result getWeekData() {
        try {
            List<DailyVo> weekData = service.getWeekData();
            return Result.ok().data("data",weekData);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/getMonthData/{year}")
    public Result getMonthData(@PathVariable String year){
        try {
            List<MonthVo> monthData = service.getMonthData(year);
            return Result.ok().data("month",monthData);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/getYear")
    public Result getYear(){
        try {
            HashSet<String> years = service.getYear();
            return Result.ok().data("years",years);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}

