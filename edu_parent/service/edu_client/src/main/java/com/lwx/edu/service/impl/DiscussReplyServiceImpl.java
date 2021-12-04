package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.DiscussReply;
import com.lwx.edu.entity.vo.ReplyVo;
import com.lwx.edu.mapper.DiscussReplyMapper;
import com.lwx.edu.service.DiscussReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-11-09
 */
@Service
@Slf4j
public class DiscussReplyServiceImpl extends ServiceImpl<DiscussReplyMapper, DiscussReply> implements DiscussReplyService {

    @Resource
    private DiscussReplyMapper discussReplyMapper;

    @Override
    public Integer getMark() {
        return discussReplyMapper.getMark();
    }

    @Override
    public void reply(DiscussReply discussReply) {
//        Integer replyId = discussReply.getReplyId();
//        Integer mark = discussReply.getMark();
//        if (replyId == 0 && mark == -1) {
//            discussReply.setMark(this.getMark());
//        }
        baseMapper.insert(discussReply);
    }

    @Override
    public List<ReplyVo> getReplyById(String id) {
        QueryWrapper<DiscussReply> wrapper = new QueryWrapper<>();
        wrapper.eq("publish_id", id);
        return discussReplyMapper.getReplyById(wrapper);
//        ArrayList<ReplyVo> child = this.getChild(reply, id);
//        return child;
    }

//    public ArrayList<ReplyVo> getChild(List<ReplyVo> answer, String id) {
//        ArrayList<ReplyVo> list = new ArrayList<>();
//        for (ReplyVo reply : answer) {
//            QueryWrapper<DiscussReply> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("publish_id", id).eq("mark", reply.getMark()).ne("reply_id", 0);
//            List<ReplyVo> temp = discussReplyMapper.getReplyById(queryWrapper);
//            reply.setList(temp);
//            list.add(reply);
//        }
//        return list;
//    }
}
