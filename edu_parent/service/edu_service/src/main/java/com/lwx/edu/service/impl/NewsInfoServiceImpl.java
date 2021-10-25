package com.lwx.edu.service.impl;

import com.lwx.edu.entity.NewsInfo;
import com.lwx.edu.entity.vo.NewsVo;
import com.lwx.edu.mapper.NewsInfoMapper;
import com.lwx.edu.service.NewsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-21
 */
@Service
public class NewsInfoServiceImpl extends ServiceImpl<NewsInfoMapper, NewsInfo> implements NewsInfoService {

    @Resource
    private NewsInfoMapper newsInfoMapper;

    @Override
    public Integer addInfo(NewsInfo newsInfo) {
        baseMapper.insert(newsInfo);
        return getLastId();
    }

    @Override
    public Integer getLastId() {
        return newsInfoMapper.getLastId();
    }

    @Override
    public NewsVo getNewsInfo(String id) {
        return newsInfoMapper.getNewsInfo(id);
    }

    @Override
    public void publishNews(String id) {
        newsInfoMapper.publishNews(id);
    }

    @Override
    public List<NewsVo> getAllNews() {
        return newsInfoMapper.getAllNews();
    }

}
