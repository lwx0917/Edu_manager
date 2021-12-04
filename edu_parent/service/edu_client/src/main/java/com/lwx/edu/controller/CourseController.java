package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lwx.edu.entity.Subject;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.*;
import com.lwx.edu.service.CourseService;
import com.lwx.edu.service.HistoryService;
import com.lwx.edu.service.SubjectService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/server/course")
@CrossOrigin
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private SubjectService subjectService;

    @GetMapping("/getHotCourse")
    public Result getHotCourse() {
        List<HotCourseVo> course = courseService.getHotCourse();
        return Result.ok().data("course", course);
    }

    @GetMapping("/getCourseById/{id}")
    public Result getCourseById(@PathVariable String id) {
        try {
            // 获取课程信息
            HotCourseVo course = courseService.getCourseById(id);
            // 获取课程列表
            CourseList courseList = courseService.getCourseList(id);
            return Result.ok().data("course", course).data("list", courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/getHotImage")
    public Result getHotImages() {
        List<ImageVo> images = courseService.getHotImages();
        return Result.ok().data("images", images);
    }

    @PostMapping("/searchCourse/{keyWord}")
    public Result searchCourse(@PathVariable String keyWord, @RequestBody PageQuery pageQuery) {
        try {
            IPage<SearchVo> courseIPage = courseService.searchCourse(keyWord, pageQuery);
            long total = courseIPage.getTotal();
            List<SearchVo> courses = courseIPage.getRecords();
            return Result.ok().data("total", total).data("courses", courses);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/getSubject")
    public Result getSubject() {
        try {
            List<Subject> subjects = subjectService.getHotSubject();
            return Result.ok().data("subject", subjects);
        } catch (Exception e) {
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/getCourseBySubject/{id}")
    public Result getCourseBySubject(@PathVariable String id) {
        try {
            List<HotCourseVo> courses = courseService.getCourseBySubject(id);
            return Result.ok().data("courses", courses);
        } catch (Exception e) {
            return Result.error().message("获取失败");
        }
    }

    /* ---------------------------------------------------------------------------------------- */

    @GetMapping("/getAllParentSubject")
    public Result getAllParentSubject() {
        try {
            List<Subject> subjects = subjectService.getAllParentSubject();
            return Result.ok().data("subjects", subjects);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/getChildSubject/{id}")
    public Result getChildSubject(@PathVariable String id) {
        try {
            List<Subject> subjects = subjectService.getChildSubject(id);
            return Result.ok().data("subject", subjects);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/getAllCourse")
    public Result getAllCourse() {
        List<HotCourseVo> courses = courseService.getAllCourse();
        return Result.ok().data("courses", courses);
    }

    @GetMapping("/getCourseClassify/{id}")
    public Result getCourseClassify(@PathVariable String id) {
        try {
            List<HotCourseVo> courses = courseService.getCourseClassify(id);
            return Result.ok().data("courses", courses);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/getHotWords")
    public Result getHotWords(){
        try {
            List<WordVo> hotWords = courseService.getHotWords();
            return Result.ok().data("hotWords",hotWords);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

}

