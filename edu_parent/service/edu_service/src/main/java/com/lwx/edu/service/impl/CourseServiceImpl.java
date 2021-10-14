package com.lwx.edu.service.impl;

import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.CourseDescription;
import com.lwx.edu.entity.vo.CourseVo;
import com.lwx.edu.mapper.CourseMapper;
import com.lwx.edu.service.CourseDescriptionService;
import com.lwx.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
}
