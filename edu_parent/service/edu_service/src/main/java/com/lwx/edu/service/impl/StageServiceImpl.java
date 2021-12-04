package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.Stage;
import com.lwx.edu.entity.vo.DirectionVo;
import com.lwx.edu.entity.vo.ListVo;
import com.lwx.edu.entity.vo.StageResultVo;
import com.lwx.edu.mapper.StageMapper;
import com.lwx.edu.service.ListService;
import com.lwx.edu.service.StageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-10-26
 */
@Service
@Slf4j
public class StageServiceImpl extends ServiceImpl<StageMapper, Stage> implements StageService {

    @Resource
    private StageMapper stageMapper;

    @Resource
    private ListService listService;

    @Override
    public void addCourseStage(Stage stage) {
        baseMapper.insert(stage);
    }

    @Override
    public DirectionVo getStageByCategoryId(String categoryId) {
        DirectionVo directionVo = new DirectionVo();
        QueryWrapper<Stage> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId).orderByAsc("stage");
        List<Stage> stages = baseMapper.selectList(wrapper);
        List<StageResultVo> stageResultVos = new ArrayList<>();
        for (Stage stage: stages) {
            StageResultVo stageResultVo = new StageResultVo();
            BeanUtils.copyProperties(stage,stageResultVo);
//            QueryWrapper<com.lwx.edu.entity.List> listQueryWrapper = new QueryWrapper<>();
//            listQueryWrapper.eq("stage_id",stage.getId());
//            List<com.lwx.edu.entity.List> list = listService.list(listQueryWrapper);
            List<ListVo> list = listService.getListInfo(stage.getId());
            stageResultVo.setLists(list);
            stageResultVos.add(stageResultVo);
        }
        directionVo.setStageList(stageResultVos);
        log.error(directionVo.toString());
        return directionVo;
    }

    @Override
    public Map<String, Integer> getCount(String categoryId) {
        QueryWrapper<Stage> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",categoryId);
        Integer stage = baseMapper.selectCount(wrapper);
        QueryWrapper<com.lwx.edu.entity.List> wrapper2 = new QueryWrapper<>();
        wrapper.eq("category_id",categoryId);
        Integer count = listService.count(wrapper2);
        Map<String, Integer> map = new HashMap<>();
        map.put("stage",stage);
        map.put("list",count);
        return map;
    }
}
