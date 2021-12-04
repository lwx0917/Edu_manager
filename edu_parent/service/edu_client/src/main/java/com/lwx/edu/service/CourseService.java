package com.lwx.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.*;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-20
 */
public interface CourseService extends IService<Course> {

    List<HotCourseVo> getHotCourse();

    List<ImageVo> getHotImages();

    List<HotCourseVo> getAllCourse();

    HotCourseVo getCourseById(String id);

    List<HotCourseVo> getCourseBySubject(String id);

    CourseList getCourseList(String id);

    List<HotCourseVo> getCourseClassify(String id);

    IPage<SearchVo> searchCourse(String keyWord, PageQuery pageQuery);

    List<WordVo> getHotWords();
}
