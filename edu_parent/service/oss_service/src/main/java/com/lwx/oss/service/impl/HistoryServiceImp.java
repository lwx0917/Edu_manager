package com.lwx.oss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.oss.entity.History;
import com.lwx.oss.mapper.HistoryMapper;
import com.lwx.oss.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HistoryServiceImp extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public int insertHistory(History history) {
        baseMapper.insert(history);
        QueryWrapper<History> wrapper = new QueryWrapper<>();
        wrapper.select("id").orderByDesc("gmt_create").last("limit 1");
        return baseMapper.selectOne(wrapper).getId();
    }
}
