package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Teacher;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.TeacherQuery;
import com.lwx.edu.mapper.TeacherMapper;
import com.lwx.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lwx
 * @since 2021-10-06
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public void pageRefer(Page<Teacher> page, TeacherQuery teacherQuery) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("gmt_create");
        if (teacherQuery == null) {
            baseMapper.selectPage(page, wrapper);
            return;
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (level != null) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(page, wrapper);
    }

    @Override
    public Integer verityIsExist(String name) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public IPage<Teacher> teacherPage(PageQuery pageQuery) {
        IPage<Teacher> page = new Page<>(pageQuery.getCurrent(), pageQuery.getSize());
        return baseMapper.selectPage(page,null);
    }
}
