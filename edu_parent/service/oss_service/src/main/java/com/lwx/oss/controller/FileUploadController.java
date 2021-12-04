package com.lwx.oss.controller;

import com.lwx.oss.entity.History;
import com.lwx.oss.service.FileService;
import com.lwx.oss.service.HistoryService;
import com.lwx.oss.service.StatisticsDailyService;
import com.lwx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/oss/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @Autowired
    private StatisticsDailyService dailyService;

    @Autowired
    private HistoryService historyService;

    @PostMapping("/uploadAvatar")
    public Result updateAvatar(MultipartFile file) {
        String url = fileService.uploadAvatar(file);
        return Result.ok().message("上传成功").data("url", url);
    }

    @PostMapping("/uploadVideo")
    public Result uploadVideo(MultipartFile file) {
        String videoId = fileService.uploadVideo(file);
        return Result.ok().message("上传成功").data("videoId", videoId);
    }

    @PostMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable String id, @RequestBody History history) {
        try {
            String video = fileService.getVideoById(id);
            int result = historyService.insertHistory(history);
            dailyService.updateVideo();
            return Result.ok().data("video", video).data("historyId",result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}
