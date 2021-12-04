package com.lwx.edu.controller;


import com.lwx.edu.entity.List;
import com.lwx.edu.entity.Stage;
import com.lwx.edu.entity.vo.DirectionVo;
import com.lwx.edu.entity.vo.StageVo;
import com.lwx.edu.service.ListService;
import com.lwx.edu.service.StageService;
import com.lwx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-10-26
 */
@RestController
@RequestMapping("/edu/stage")
@CrossOrigin
@Slf4j
public class StageController {

    @Resource
    private StageService stageService;

    @Resource
    private ListService listService;

    @PostMapping("/addCourseStage")
    public Result addCourseStage(@RequestBody StageVo stageVo) {
        try {
            log.error(stageVo.toString());
            Stage stage = new Stage();
            BeanUtils.copyProperties(stageVo, stage);
            log.error(stage.toString());
            // 添加阶段
            stageService.addCourseStage(stage);
            // 添加课程
            for (String courseId : stageVo.getCourseList()) {
                List list = new List();
                BeanUtils.copyProperties(stage, list);
                list.setStageId(stage.getId());
                list.setCourseId(courseId);
                listService.addList(list);
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("添加失败");
        }
    }

    @GetMapping("/getStageByCategoryId/{categoryId}")
    public Result getStageByCategoryId(@PathVariable String categoryId) {
        try {
            DirectionVo direction = stageService.getStageByCategoryId(categoryId);
            return Result.ok().data("direction", direction);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/getCount/{categoryId}")
    public Result getCount(@PathVariable String categoryId) {
        try {
            Map<String, Integer> count = stageService.getCount(categoryId);
            return Result.ok().data("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}

