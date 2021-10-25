package com.lwx.edu.service;

import com.lwx.edu.entity.NewsContent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lwx
 * @since 2021-10-21
 */
public interface NewsContentService extends IService<NewsContent> {

    Integer addContent(NewsContent newsContent);
}
