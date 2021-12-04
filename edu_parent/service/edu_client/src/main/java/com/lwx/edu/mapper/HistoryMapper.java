package com.lwx.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.edu.entity.History;
import com.lwx.edu.entity.vo.HistoryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-20
 */
public interface HistoryMapper extends BaseMapper<History> {

    List<HistoryVo> getHistoryByUserId(@Param(Constants.WRAPPER) QueryWrapper<HistoryVo> wrapper);

    void updateSecond(String id, String second);
}
