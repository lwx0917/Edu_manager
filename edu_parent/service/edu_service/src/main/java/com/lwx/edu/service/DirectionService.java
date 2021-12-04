package com.lwx.edu.service;

import com.lwx.edu.entity.Direction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-10-25
 */
public interface DirectionService extends IService<Direction> {

    List<Map<String, Object>> getParentDirection();

    void addDirection(Direction direction);

    List<Map<String, Object>> getDirection();

    List<Map<String, Object>> getHotDirection();
}
