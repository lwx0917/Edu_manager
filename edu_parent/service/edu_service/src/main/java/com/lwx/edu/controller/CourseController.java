package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.CourseDescription;
import com.lwx.edu.entity.vo.CourseVo;
import com.lwx.edu.service.CourseDescriptionService;
import com.lwx.edu.service.CourseService;
import com.lwx.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String courseId = courseService.addCourseInfo(courseVo);
        if (!StringUtils.isEmpty(courseId)) {
            return Result.ok().data("courseId", courseId);
        }
        return Result.error().message("添加失败");
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
}

