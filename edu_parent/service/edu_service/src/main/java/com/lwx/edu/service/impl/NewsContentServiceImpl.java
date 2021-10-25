package com.lwx.edu.service.impl;

import com.lwx.edu.entity.NewsContent;
import com.lwx.edu.mapper.NewsContentMapper;
import com.lwx.edu.service.NewsContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lwx
 * @since 2021-10-21
 */
@Service
public class NewsContentServiceImpl extends ServiceImpl<NewsContentMapper, NewsContent> implements NewsContentService {

    @Override
    public Integer addContent(NewsContent newsContent) {
        return baseMapper.insert(newsContent);
    }
}
