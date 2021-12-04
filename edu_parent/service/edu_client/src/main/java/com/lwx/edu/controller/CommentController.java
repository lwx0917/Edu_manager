package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lwx.edu.entity.Comment;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.service.CommentService;
import com.lwx.edu.utils.BaseUtils;
import com.lwx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-11-15
 */
@RestController
@Slf4j
@RequestMapping("/server/comment")
@CrossOrigin
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/addComment")
    public Result addComment(@RequestBody Map<String,Object> map) {
        try {
            commentService.addComment(map);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/getComments/{courseId}")
    public Result getComments(@PathVariable String courseId, @RequestParam Map<String,Object> map) {
        try {
            PageQuery page = BaseUtils.getValue(map.get("map").toString());
            log.error(page.toString());
            IPage<Comment> comment = commentService.getComments(courseId, page);
            List<Comment> comments = comment.getRecords();
            return Result.ok().data("comments", comments).data("total",comment.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/getCounts/{courseId}")
    public Result getCounts(@PathVariable String courseId){
        try {
            Integer count = commentService.getCounts(courseId);
            return Result.ok().data("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}

