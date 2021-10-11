package com.lwx.oss.controller;

import com.lwx.oss.service.FileService;
import com.lwx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadAvatar")
    public Result updateAvatar(MultipartFile file) {
        String url = fileService.uploadAvatar(file);
        return Result.ok().message("上传成功").data("url", url);
    }
}
