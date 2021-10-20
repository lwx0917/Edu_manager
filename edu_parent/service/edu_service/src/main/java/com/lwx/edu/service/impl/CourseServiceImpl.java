package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.CourseDescription;
import com.lwx.edu.entity.vo.CourseQuery;
import com.lwx.edu.entity.vo.CourseVo;
import com.lwx.edu.entity.vo.PublishInfoVo;
import com.lwx.edu.entity.vo.ResultCourseVo;
import com.lwx.edu.mapper.CourseMapper;
import com.lwx.edu.service.ChapterService;
import com.lwx.edu.service.CourseDescriptionService;
import com.lwx.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.edu.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lwx
 * @since 2021-10-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private VideoService videoService;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public String addCourseInfo(CourseVo courseVo) {
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseVo, course);
        this.save(course);

        CourseDescription description = new CourseDescription();
        description.setDescription(courseVo.getDescription());
        description.setId(course.getId());
        courseDescriptionService.save(description);
        return course.getId();
    }

    @Override
    public String updateCourseInfo(CourseVo courseVo) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("id", courseVo.getId());
        Course course = new Course();
        BeanUtils.copyProperties(courseVo, course);
        this.update(course, wrapper);

        CourseDescription description = new CourseDescription();
        BeanUtils.copyProperties(courseVo, description);
        courseDescriptionService.updateById(description);

        return course.getId();
    }

    @Override
    public PublishInfoVo getPublishInfo(String id) {
        return courseMapper.getPublishInfo(id);
    }

    @Override
    public void publishCourse(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        baseMapper.updateById(course);
    }

    @Override
    public void pageRefer(Page<ResultCourseVo> page, CourseQuery courseQuery) {
        if (courseQuery == null) {
            courseMapper.selectCourseInfoNoArg(page);
            return;
        }
        QueryWrapper<CourseQuery> wrapper = new QueryWrapper<>();
        wrapper.eq("t.is_deleted", 0);
        String name = courseQuery.getName();
        String minLessonNum = courseQuery.getMinLessonNum();
        String maxLessonNum = courseQuery.getMaxLessonNum();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("title", name);
        }

        if (!StringUtils.isEmpty(minLessonNum) && !StringUtils.isEmpty(maxLessonNum)) {
            wrapper.between("lesson_Num", minLessonNum, maxLessonNum);
            courseMapper.selectCourseInfo(page, wrapper);
            return;
        }

        if (!StringUtils.isEmpty(minLessonNum)) {
            wrapper.ge("lesson_Num", minLessonNum);
        }

        if (!StringUtils.isEmpty(maxLessonNum)) {
            wrapper.le("lesson_Num", maxLessonNum);
        }

        courseMapper.selectCourseInfo(page, wrapper);
    }

    @Override
    public void delCourse(String id) {
        baseMapper.deleteById(id);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        chapterService.remove(wrapper);
        videoService.remove(wrapper);
    }
}
