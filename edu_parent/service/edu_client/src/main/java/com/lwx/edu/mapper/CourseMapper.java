package com.lwx.edu.mapper;

import com.lwx.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.vo.CourseInfoVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-20
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseInfoVo> getHotCourse();

    List<String> getHotImages();
}
