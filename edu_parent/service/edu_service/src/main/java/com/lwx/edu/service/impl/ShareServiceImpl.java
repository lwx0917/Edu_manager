package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Share;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.mapper.ShareMapper;
import com.lwx.edu.service.ShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lwx
 * @since 2021-11-25
 */
@Service
@Slf4j
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {

    @Override
    public IPage<Share> getSharePage(PageQuery pageQuery) {
        Page<Share> page = new Page<>(pageQuery.getCurrent(), pageQuery.getSize());
        return baseMapper.selectPage(page, null);
    }


    @Override
    public Share getShareById(String id) {
        QueryWrapper<Share> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public void updateShare(String id, Share share) {
        QueryWrapper<Share> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        baseMapper.update(share, wrapper);
    }
}
