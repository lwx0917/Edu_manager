package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Teacher;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.TeacherQuery;
import com.lwx.edu.service.TeacherService;
import com.lwx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-06
 */
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/verityIsExist/{name}")
    public Result verityIsExist(@PathVariable String name) {
        try {
            Integer count = teacherService.verityIsExist(name);
            if (count == 0) {
                return Result.ok().message("教师名称可用");
            } else {
                return Result.ok().message("教师名称已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("验证错误");
        }
    }

    // 查询所有
    @GetMapping("/findAll")
    public Result findAll() {
        List<Teacher> teachers = teacherService.list(null);
        return Result.ok().data("teachers", teachers);
    }

    @PostMapping("/teacherPage")
    public Result teacherPage(@RequestBody PageQuery pageQuery) {
        log.error(pageQuery.toString());
        IPage<Teacher> teacher = teacherService.teacherPage(pageQuery);
        List<Teacher> teachers = teacher.getRecords();
        long total = teacher.getTotal();
        return Result.ok().data("teachers", teachers).data("total", total);
    }

    // 根据Id查询教师信息
    @GetMapping("/findById/{id}")
    public Result findTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok().data("teacher", teacher);
    }

    // 修改教师信息
    @PostMapping("/updateById")
    public Result updateTeacherById(@RequestBody Teacher teacher) {
        boolean mark = teacherService.updateById(teacher);
        return mark ? Result.ok() : Result.error();
    }

    // 添加教师
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        return save ? Result.ok() : Result.error();
    }

    // 根据Id移除教师
    @DeleteMapping("{id}")
    public Result removeTeacherById(@PathVariable String id) {
        return teacherService.removeById(id) ? Result.ok() : Result.error();
    }

    // 搜索分页
    @PostMapping("{currentPage}/{limit}")
    public Result findPage(@PathVariable long currentPage, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<Teacher> page = new Page<>(currentPage, limit);
        teacherService.pageRefer(page, teacherQuery);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return Result.ok().data(map);
    }

}

