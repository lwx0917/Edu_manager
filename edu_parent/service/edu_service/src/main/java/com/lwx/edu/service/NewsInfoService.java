package com.lwx.edu.service;

import com.lwx.edu.entity.NewsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.NewsVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-21
 */
public interface NewsInfoService extends IService<NewsInfo> {

    Integer addInfo(NewsInfo newsInfo);

    Integer getLastId();

    NewsVo getNewsInfo(String id);

    void publishNews(String id);

    List<NewsVo> getAllNews();
}
