package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lwx.edu.entity.DiscussPublish;
import com.lwx.edu.entity.DiscussReply;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.DiscussVo;
import com.lwx.edu.entity.vo.ReplyVo;
import com.lwx.edu.service.DiscussGoodService;
import com.lwx.edu.service.DiscussPublishService;
import com.lwx.edu.service.DiscussReplyService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-11-09
 */

@CrossOrigin
@RestController
@RequestMapping("/server/discuss")
public class DiscussController {

    @Resource
    private DiscussPublishService discussPublishService;

    @Resource
    private DiscussReplyService discussReplyService;

    @Resource
    private DiscussGoodService discussGoodService;

    @PostMapping("/publish")
    public Result publish(@RequestBody DiscussPublish discuss) {
        try {
            discussPublishService.publish(discuss);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("发生错误");
        }
    }

    @PostMapping("/reply")
    public Result reply(@RequestBody DiscussReply discussReply) {
        try {
            discussReplyService.reply(discussReply);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("error");
        }
    }

    @PostMapping("/good/{id}/{userId}")
    public Result good(@PathVariable String id, @PathVariable String userId) {
        try {
            discussPublishService.good(id, userId);
            discussGoodService.add(id, userId);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/bed/{id}/{userId}")
    public Result bed(@PathVariable String id, @PathVariable String userId) {
        try {
            discussPublishService.bed(id, userId);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /* ------------------------------------------------------------------ */


    @PostMapping("/getDiscuss")
    public Result getDiscuss(@RequestBody PageQuery discussPageQuery) {
        try {

//            List<DiscussVo> discussVos = discussPublishService.getDisscussList();
            IPage<DiscussVo> discuss = discussPublishService.getDiscuss(discussPageQuery);
            long total = discuss.getTotal();
            List<DiscussVo> records = discuss.getRecords();
            return Result.ok().data("total", total).data("records", records);
//            return Result.ok().data("discuss", discussVos);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/getDiscussById/{id}")
    public Result getDiscussById(@PathVariable String id) {
        try {
            DiscussVo discuss = discussPublishService.getDiscussById(id);
            // reply
            List<ReplyVo> reply = discussReplyService.getReplyById(id);
            return Result.ok().data("problem", discuss).data("reply", reply);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/confirmGood/{publishId}/{userId}")
    public Result confirmGood(@PathVariable String publishId, @PathVariable String userId) {
        Integer result = discussGoodService.confirm(publishId, userId);
        return Result.ok().data("flag", result == 0);
    }

}

