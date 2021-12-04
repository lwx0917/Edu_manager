package com.lwx.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.ListVo;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-15
 */
public interface ListService extends IService<com.lwx.edu.entity.List> {

    void addList(com.lwx.edu.entity.List list);

    List<ListVo> getListInfo(Integer stageId);
}
