package com.lwx.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-20
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<HotCourseVo> getHotCourse();

    List<ImageVo> getHotImages();

    List<HotCourseVo> getAllCourse();

//    void searchCourse(Page<Course> page, String keyWord);

    HotCourseVo getCourseById(String id);

    List<HotCourseVo> getCourseBySubject(String id);

    List<HotCourseVo> getCourseClassify(String id);

    IPage<SearchVo> selectCourses(Page<SearchVo> page, @Param(Constants.WRAPPER)QueryWrapper<SearchVo> keyWord);

    List<WordVo> getHotWords();
}
