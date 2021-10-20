package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.CourseDescription;
import com.lwx.edu.entity.vo.CourseQuery;
import com.lwx.edu.entity.vo.CourseVo;
import com.lwx.edu.entity.vo.PublishInfoVo;
import com.lwx.edu.entity.vo.ResultCourseVo;
import com.lwx.edu.service.CourseDescriptionService;
import com.lwx.edu.service.CourseService;
import com.lwx.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author lwx
 * @since 2021-10-13
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseVo courseVo) {
        try {
            String courseId = courseService.addCourseInfo(courseVo);
            if (!StringUtils.isEmpty(courseId)) {
                return Result.ok().data("courseId", courseId);
            }
            return Result.error().message("添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("请输入正确信息");
        }

    }

    @GetMapping("/getCourseInfo/{id}")
    public Result getCourseInfo(@PathVariable String id) {
        CourseVo courseVo = new CourseVo();
        Course course = courseService.getById(id);
        CourseDescription courseDesc = courseDescriptionService.getById(id);
        BeanUtils.copyProperties(course, courseVo);
        courseVo.setDescription(courseDesc.getDescription());
        return Result.ok().data("course", courseVo);
    }

    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseVo courseVo) {
        String courseId = courseService.updateCourseInfo(courseVo);
        return Result.ok().data("course", courseId);
    }

    @GetMapping("/getPublishInfo/{id}")
    public Result getPublishInfo(@PathVariable String id) {
        PublishInfoVo vo = courseService.getPublishInfo(id);
        return Result.ok().data("publishInfo", vo);
    }

    @PutMapping("/publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {
        courseService.publishCourse(id);
        return Result.ok();
    }

    @PostMapping("/getAllCourse/{current}/{limit}")
    public Result getAllCourse(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) CourseQuery courseQuery) {
        Page<ResultCourseVo> page = new Page<>(current, limit);
        courseService.pageRefer(page, courseQuery);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return Result.ok().data(map);
    }

    @DeleteMapping("/delCourse/{id}")
    public Result delCourse(@PathVariable String id) {
        courseService.delCourse(id);
        return Result.ok();
    }
}

