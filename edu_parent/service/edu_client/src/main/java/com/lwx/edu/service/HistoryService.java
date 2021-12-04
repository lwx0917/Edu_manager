package com.lwx.edu.service;

import com.lwx.edu.entity.vo.HistoryVo;
import com.lwx.edu.entity.History;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author lwx
 * @since 2021-11-20
 */
public interface HistoryService extends IService<History> {


    Map<String, HistoryVo> getHistoryByUserId(String userId);

    void updateSecond(String id, String second);

//    void insertHistory(String id, String userId);
}
