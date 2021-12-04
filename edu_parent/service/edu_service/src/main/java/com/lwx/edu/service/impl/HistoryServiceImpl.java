package com.lwx.edu.service.impl;

import com.lwx.edu.entity.History;
import com.lwx.edu.entity.vo.HistoryVo;
import com.lwx.edu.mapper.HistoryMapper;
import com.lwx.edu.service.HistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lwx
 * @since 2021-11-20
 */
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Override
    public void insertHistory(History history) {
        baseMapper.insert(history);
    }
}
