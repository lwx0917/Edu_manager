package com.lwx.edu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.DiscussPublish;
import com.lwx.edu.entity.vo.DiscussVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-09
 */
public interface DiscussPublishMapper extends BaseMapper<DiscussPublish> {

    void good(String id, String userId);

    void bed(String id, String userId);

    IPage<DiscussVo> getDiscuss(IPage<DiscussVo> page);

    DiscussVo getDiscussById(String id);

    List<DiscussVo> getDiscussList();
}
