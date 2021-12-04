package com.lwx.edu.service.impl;

import com.lwx.edu.entity.List;
import com.lwx.edu.entity.vo.ListVo;
import com.lwx.edu.mapper.ListMapper;
import com.lwx.edu.service.ListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lwx
 * @since 2021-11-15
 */
@Service
public class ListServiceImpl extends ServiceImpl<ListMapper, List> implements ListService {

    @Resource
    private ListMapper listMapper;

    @Override
    public void addList(List list) {
        baseMapper.insert(list);
    }

    @Override
    public java.util.List<ListVo> getListInfo(Integer stageId) {
        return listMapper.getListInfo(stageId);
    }


}
