package com.lwx.edu.controller;


import com.lwx.edu.entity.Video;
import com.lwx.edu.service.VideoService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lwx
 * @since 2021-11-08
 */
@RestController
@RequestMapping("/server/video")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;

    @GetMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable String id){
        try {
            Video video = videoService.getById(id);
            return Result.ok().data("video",video);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}

