package com.lwx.edu.service;

import com.lwx.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.CourseVo;

/**
 * @author lwx
 * @since 2021-10-13
 */
public interface CourseService extends IService<Course> {

    String addCourseInfo(CourseVo courseVo);

    String updateCourseInfo(CourseVo courseVo);
}
