package com.lwx.edu.controller;


import com.lwx.edu.entity.vo.HistoryVo;
import com.lwx.edu.service.HistoryService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-11-20
 */
@CrossOrigin
@RestController
@RequestMapping("/server/history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @GetMapping("/getHistoryByUserId/{userId}")
    public Result getHistoryByUserId(@PathVariable String userId) {
        try {
            Map<String, HistoryVo> history = historyService.getHistoryByUserId(userId);
            return Result.ok().data("history", history);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/updateSecond/{id}/{second}")
    public Result updateSecond(@PathVariable String id,@PathVariable String second){
        try {
            historyService.updateSecond(id,second);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}

