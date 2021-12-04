package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.DiscussGood;
import com.lwx.edu.mapper.DiscussGoodMapper;
import com.lwx.edu.service.DiscussGoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lwx
 * @since 2021-11-24
 */
@Service
public class DiscussGoodServiceImpl extends ServiceImpl<DiscussGoodMapper, DiscussGood> implements DiscussGoodService {

    @Override
    public Integer confirm(String publishId, String userId) {
        QueryWrapper<DiscussGood> wrapper = new QueryWrapper<>();
        wrapper.eq("publish_id",publishId).eq("user_id",userId);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public void add(String id, String userId) {
        DiscussGood good = new DiscussGood();
        good.setPublishId(id);
        good.setUserId(userId);
        baseMapper.insert(good);
    }
}
