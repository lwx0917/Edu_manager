package com.lwx.edu.service;

import com.lwx.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.CourseInfoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lwx
 * @since 2021-10-20
 */
public interface CourseService extends IService<Course> {

    List<CourseInfoVo> getHotCourse();
}
