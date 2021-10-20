package com.lwx.edu.service.impl;

import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.vo.CourseInfoVo;
import com.lwx.edu.mapper.CourseMapper;
import com.lwx.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2021-10-20
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<CourseInfoVo> getHotCourse() {
        return courseMapper.getHotCourse();
    }
}