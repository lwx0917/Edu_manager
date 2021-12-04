package com.lwx.edu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.Video;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-08
 */
public interface VideoService extends IService<Video> {

    List<Video> getVideo(String id);
}
