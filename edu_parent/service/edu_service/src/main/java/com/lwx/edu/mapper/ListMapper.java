package com.lwx.edu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.vo.ListVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-15
 */
public interface ListMapper extends BaseMapper<com.lwx.edu.entity.List> {

    List<ListVo> getListInfo(Integer stageId);
}
