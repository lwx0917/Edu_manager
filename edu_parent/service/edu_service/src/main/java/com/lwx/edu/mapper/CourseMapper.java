package com.lwx.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lwx
 * @since 2021-10-13
 */
public interface CourseMapper extends BaseMapper<Course> {

    PublishInfoVo getPublishInfo(String id);

    IPage<ResultCourseVo> selectCourseInfo(IPage<ResultCourseVo> page, @Param(Constants.WRAPPER) Wrapper<CourseQuery> wrapper);

    IPage<ResultCourseVo> selectCourseInfoNoArg(IPage<ResultCourseVo> page);

    List<CourseInfoVo> getCourseBySubjectId(String id);
}
