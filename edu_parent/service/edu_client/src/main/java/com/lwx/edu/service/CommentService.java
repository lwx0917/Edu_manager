package com.lwx.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lwx.edu.entity.Comment;
import com.lwx.edu.entity.query.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author lwx
 * @since 2021-11-15
 */
public interface CommentService extends IService<Comment> {

    IPage<Comment> getComments(String courseId, PageQuery pageQuery);

    void addComment(Map<String, Object> map);

    Integer getCounts(String courseId);
}
