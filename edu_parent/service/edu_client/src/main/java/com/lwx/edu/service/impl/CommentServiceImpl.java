package com.lwx.edu.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Comment;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.mapper.CommentMapper;
import com.lwx.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lwx
 * @since 2021-11-15
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public IPage<Comment> getComments(String courseId, PageQuery pageQuery) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        Page<Comment> page = new Page<>(pageQuery.getCurrent(), pageQuery.getSize());
        wrapper.eq("course_id", courseId);
        wrapper.orderByDesc("gmt_create");
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public void addComment(Map<String, Object> map) {
        log.error(map.toString());
        String json = map.get("map").toString();
        Comment bean = JSONUtil.toBean(json, Comment.class);
        log.error(bean.toString());
        Comment comment = new Comment();
        BeanUtils.copyProperties(bean, comment);
        baseMapper.insert(comment);
    }

    @Override
    public Integer getCounts(String courseId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        return baseMapper.selectCount(wrapper);
    }
}
