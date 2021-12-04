package com.lwx.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.TeacherQuery;

/**
 * @author lwx
 * @since 2021-10-06
 */
public interface TeacherService extends IService<Teacher> {

    void pageRefer(Page<Teacher> page, TeacherQuery teacherQuery);

    Integer verityIsExist(String name);

    IPage<Teacher> teacherPage(PageQuery pageQuery);
}
