package com.lwx.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.CourseInfoVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-20
 */
public interface CourseService extends IService<Course> {

    List<CourseInfoVo> getHotCourse();

    List<String> getHotImages();

    void searchCourse(Page<Course> page, String keyWord);
}
