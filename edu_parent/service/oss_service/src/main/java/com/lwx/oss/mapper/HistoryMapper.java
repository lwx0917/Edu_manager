package com.lwx.oss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.oss.entity.History;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
    void insertHistory(History history);
}
