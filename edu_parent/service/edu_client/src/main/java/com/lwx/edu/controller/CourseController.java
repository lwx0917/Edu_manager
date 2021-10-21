package com.lwx.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.vo.CourseInfoVo;
import com.lwx.edu.entity.vo.CourseVo;
import com.lwx.edu.service.CourseService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
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

    @GetMapping("/getHotImage")
    public Result getHotImages() {
        List<String> images = courseService.getHotImages();
        return Result.ok().data("images", images);
    }

    @GetMapping("/searchCourse/{currentPage}/{limit}/{keyWord}")
    public Result searchCourse(@PathVariable long currentPage, @PathVariable long limit, @PathVariable String keyWord) {
        Page<Course> page = new Page<>(currentPage, limit);
        courseService.searchCourse(page, keyWord);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return Result.ok().data("course", map);
    }
}

