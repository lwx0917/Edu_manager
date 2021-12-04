package com.lwx.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.oss.entity.History;
import org.springframework.stereotype.Component;

@Component
public interface HistoryService extends IService<History> {
    int insertHistory(History history);
}
