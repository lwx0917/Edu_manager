package com.lwx.edu.mapper;

import com.lwx.edu.entity.NewsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.vo.NewsVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-21
 */
public interface NewsInfoMapper extends BaseMapper<NewsInfo> {



    Integer getLastId();

    NewsVo getNewsInfo(String id);

    void publishNews(String id);

    List<NewsVo> getAllNews();
}
