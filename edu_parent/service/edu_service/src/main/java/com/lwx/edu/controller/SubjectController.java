package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.Subject;
import com.lwx.edu.entity.vo.SubjectNestedVo;
import com.lwx.edu.service.SubjectService;
import com.lwx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // 获取所有分类
    @GetMapping("/getSubjects")
    public Result getSubjects() {
        List<SubjectNestedVo> Subjects = subjectService.getSubjects();
        return Result.ok().data("items", Subjects);
    }

    // 获取父分类
    @GetMapping("/getParentSubject")
    public Result getParentSubject() {
        List<Subject> parentSubjects = subjectService.getParentSubject();
        return Result.ok().data("classify", parentSubjects);
    }

    // 添加分类
    @PostMapping("/addSubject")
    public Result addSubject(@RequestBody Subject subject) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", subject.getTitle());
        int count = subjectService.count(wrapper);
        if (count == 0) {
            boolean result = subjectService.save(subject);
            return result ? Result.ok() : Result.error();
        } else {
            return Result.error().message("当前分类已存在");
        }
    }

    // 导入分类
    @PostMapping("/importSubjects")
    public Result importSubjects(MultipartFile file) {
        subjectService.importSubjects(file, subjectService);
        return Result.ok();
    }

}

