package com.lwx.edu.service;

import com.lwx.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-13
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> nestedList(String id);

    void removeChapterById(String id) throws Exception;
}
