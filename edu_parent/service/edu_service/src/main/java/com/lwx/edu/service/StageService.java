package com.lwx.edu.service;

import com.lwx.edu.entity.Stage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.DirectionVo;

import java.util.Map;

/**
 * @author lwx
 * @since 2021-10-26
 */
public interface StageService extends IService<Stage> {

    void addCourseStage(Stage stage);

    DirectionVo getStageByCategoryId(String categoryId);

    Map<String, Integer> getCount(String categoryId);
}
