package com.lwx.edu.config;

import cn.hutool.core.date.DateUtil;
import com.lwx.edu.service.StatisticsDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Resource
    private StatisticsDailyService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Integer exist = service.isExist(DateUtil.today());
        if(exist == 0){
            service.create();
        }
    }
}
