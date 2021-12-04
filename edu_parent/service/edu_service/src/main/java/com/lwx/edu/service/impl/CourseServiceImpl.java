package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.CourseDescription;
import com.lwx.edu.entity.Subject;
import com.lwx.edu.entity.vo.*;
import com.lwx.edu.mapper.CourseMapper;
import com.lwx.edu.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Resource
    private SubjectService subjectService;

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

    @Override
    public List<CourseVo> getCourses() {
        return null;
    }

    @Override
    public CourseListVo getCourseList() {
        QueryWrapper<Subject> subjectWrapper = new QueryWrapper<>();
        subjectWrapper.eq("parent_id",0);
        CourseListVo courseListVo = new CourseListVo();
        ArrayList<SubjectListVo> subjectListVos = new ArrayList<>();
        courseListVo.setCourseList(subjectListVos);
        // 1
        List<Subject> parentSubjectList = subjectService.list(subjectWrapper);
        for(Subject subject:parentSubjectList){
            QueryWrapper<Subject> subjectWrapper2 = new QueryWrapper<>();
            subjectWrapper2.eq("parent_id",subject.getId());
            // 2
            List<Subject> subjectList = subjectService.list(subjectWrapper2);
            SubjectListVo subjectListVo = new SubjectListVo();
            BeanUtils.copyProperties(subject,subjectListVo);
            ArrayList<ChildSubjectVo> childSubjectVos = new ArrayList<>();
            for(Subject childSubject:subjectList){
                // 3
                List<CourseInfoVo> courseBySubjectId = baseMapper.getCourseBySubjectId(childSubject.getId());
                ChildSubjectVo childSubjectVo = new ChildSubjectVo();
                BeanUtils.copyProperties(childSubject,childSubjectVo);
                childSubjectVo.setChildren(courseBySubjectId);
                childSubjectVos.add(childSubjectVo);
            }
            subjectListVo.setChildren(childSubjectVos);
            subjectListVos.add(subjectListVo);
        }
        return courseListVo;
    }

    @Override
    public List<CourseInfoVo> getCourseBySubjectId(String id) {
        return courseMapper.getCourseBySubjectId(id);
    }


}
