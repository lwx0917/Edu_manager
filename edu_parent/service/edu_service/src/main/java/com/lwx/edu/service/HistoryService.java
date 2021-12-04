package com.lwx.edu.service;

import com.lwx.edu.entity.History;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.HistoryVo;

/**
 * @author lwx
 * @since 2021-11-20
 */
public interface HistoryService extends IService<History> {

    void insertHistory(History history);
}
