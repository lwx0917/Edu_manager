package com.lwx.edu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.Chapter;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-08
 */
public interface ChapterService extends IService<Chapter> {

    List<Chapter> getChapter(String id);
}
