package com.lwx.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.DiscussReply;
import com.lwx.edu.entity.vo.ReplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-09
 */
public interface DiscussReplyMapper extends BaseMapper<DiscussReply> {

    Integer getMark();

    List<ReplyVo> getReplyById(@Param(Constants.WRAPPER)QueryWrapper<DiscussReply> wrapper);
}
