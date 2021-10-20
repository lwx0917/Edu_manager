package com.lwx.edu.service;

import com.lwx.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.VideoInfoVo;

/**
 * @author lwx
 * @since 2021-10-15
 */
public interface VideoService extends IService<Video> {

    boolean getCountByChapterId(String id);

    void saveVideoInfo(VideoInfoVo vo);

    VideoInfoVo getVideoById(String id);

    void updateVideo(VideoInfoVo vo);

}
