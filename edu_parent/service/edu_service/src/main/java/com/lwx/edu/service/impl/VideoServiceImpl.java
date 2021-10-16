package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.Video;
import com.lwx.edu.entity.vo.VideoInfoVo;
import com.lwx.edu.mapper.VideoMapper;
import com.lwx.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author lwx
 * @since 2021-10-15
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public boolean getCountByChapterId(String id) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return null != count && count > 0;
    }

    @Override
    public void saveVideoInfo(VideoInfoVo vo) {
        Video video = new Video();
        BeanUtils.copyProperties(vo, video);
        baseMapper.insert(video);
    }
}
