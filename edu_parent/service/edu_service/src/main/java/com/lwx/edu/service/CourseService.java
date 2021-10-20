package com.lwx.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.CourseQuery;
import com.lwx.edu.entity.vo.CourseVo;
import com.lwx.edu.entity.vo.PublishInfoVo;
import com.lwx.edu.entity.vo.ResultCourseVo;

/**
 * @author lwx
 * @since 2021-10-13
 */
public interface CourseService extends IService<Course> {

    String addCourseInfo(CourseVo courseVo);

    String updateCourseInfo(CourseVo courseVo);

    PublishInfoVo getPublishInfo(String id);

    void publishCourse(String id);

    void pageRefer(Page<ResultCourseVo> page, CourseQuery courseQuery);

    void delCourse(String id);
}
