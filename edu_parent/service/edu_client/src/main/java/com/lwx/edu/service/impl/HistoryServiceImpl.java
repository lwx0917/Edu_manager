package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.History;
import com.lwx.edu.entity.vo.HistoryVo;
import com.lwx.edu.mapper.HistoryMapper;
import com.lwx.edu.service.HistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-11-20
 */
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public Map<String, HistoryVo> getHistoryByUserId(String userId) {
        QueryWrapper<HistoryVo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<HistoryVo> allHistroy = historyMapper.getHistoryByUserId(wrapper);
        Map<String,HistoryVo> map = new HashMap<>();
        for (HistoryVo histroy:allHistroy){
            map.put(histroy.getCourseId(),histroy);
        }
        return map;
    }

    @Override
    public void updateSecond(String id, String second) {
        historyMapper.updateSecond(id,second);
    }


//    @Override
//    public void insertHistory(String id, String userId) {
//        History history = new History();
//        history.setCourseId(id);
//        history.setUserId(userId);
//        baseMapper.insert(history);
//    }
}
