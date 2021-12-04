package com.lwx.edu.service;

import com.lwx.edu.entity.DiscussReply;
import com.lwx.edu.entity.vo.ReplyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lwx
 * @since 2021-11-09
 */
public interface DiscussReplyService extends IService<DiscussReply> {

    Integer getMark();

    void reply(DiscussReply discussReply);

    List<ReplyVo> getReplyById(String id);
}
