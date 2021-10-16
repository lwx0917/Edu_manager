package com.lwx.edu.controller;


import com.lwx.edu.entity.vo.VideoInfoVo;
import com.lwx.edu.entity.vo.VideoVo;
import com.lwx.edu.service.VideoService;
import com.lwx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lwx
 * @since 2021-10-15
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody VideoInfoVo vo) {
        videoService.saveVideoInfo(vo);
        return Result.ok();
    }
}

