package com.lwx.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.DiscussPublish;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.DiscussVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-09
 */
public interface DiscussPublishService extends IService<DiscussPublish> {

    void publish(DiscussPublish discuss);

    void good(String id, String userId);

    void bed(String id, String userId);

    IPage<DiscussVo> getDiscuss(PageQuery discussPageQuery);

    DiscussVo getDiscussById(String id);

    List<DiscussVo> getDiscussList();
}
