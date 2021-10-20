package com.lwx.edu.controller;


import com.lwx.edu.entity.vo.CourseInfoVo;
import com.lwx.edu.entity.vo.CourseVo;
import com.lwx.edu.service.CourseService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/server/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/getHotCourse")
    public Result getHotCourse() {
        List<CourseInfoVo> course = courseService.getHotCourse();
        return Result.ok().data("course", course);
    }
}

