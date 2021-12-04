package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.DiscussPublish;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.DiscussVo;
import com.lwx.edu.mapper.DiscussPublishMapper;
import com.lwx.edu.service.DiscussPublishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-11-09
 */
@Service
public class DiscussPublishServiceImpl extends ServiceImpl<DiscussPublishMapper, DiscussPublish> implements DiscussPublishService {

    @Resource
    private DiscussPublishMapper discussPublishMapper;

    @Override
    public void publish(DiscussPublish discuss) {
        baseMapper.insert(discuss);
    }

    @Override
    public void good(String id, String userId) {
        discussPublishMapper.good(id, userId);
    }

    @Override
    public void bed(String id, String userId) {
        discussPublishMapper.bed(id, userId);
    }

    @Override
    public IPage<DiscussVo> getDiscuss(PageQuery discussPageQuery) {
//        IPage<Teacher> page = new Page<>(pageQuery.getCurrent(), pageQuery.getSize());
        IPage<DiscussVo> page = new Page<>(discussPageQuery.getCurrent(), discussPageQuery.getSize());
        return discussPublishMapper.getDiscuss(page);
    }

    @Override
    public DiscussVo getDiscussById(String id) {
        return discussPublishMapper.getDiscussById(id);
    }

    @Override
    public List<DiscussVo> getDiscussList() {
        return discussPublishMapper.getDiscussList();
    }
}
