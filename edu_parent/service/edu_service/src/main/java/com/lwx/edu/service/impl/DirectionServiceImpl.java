package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.Direction;
import com.lwx.edu.mapper.DirectionMapper;
import com.lwx.edu.service.DirectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-10-25
 */
@Service
public class DirectionServiceImpl extends ServiceImpl<DirectionMapper, Direction> implements DirectionService {

    @Resource
    private DirectionMapper directionMapper;

    @Override
    public List<Map<String, Object>> getParentDirection() {
        QueryWrapper<Direction> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title").eq("parent_id", 0);
        return baseMapper.selectMaps(wrapper);
    }

    @Override
    public void addDirection(Direction direction) {
        baseMapper.insert(direction);
    }

    @Override
    public List<Map<String, Object>> getDirection() {
        QueryWrapper<Direction> wrapper = new QueryWrapper<>();
        wrapper.ne("parent_id", 0);
        return baseMapper.selectMaps(wrapper);
    }

    @Override
    public List<Map<String, Object>> getHotDirection() {
        QueryWrapper<Direction> wrapper = new QueryWrapper<>();
        wrapper.ne("parent_id", 0);
        wrapper.last("limit 3");
        return baseMapper.selectMaps(wrapper);
    }
}
