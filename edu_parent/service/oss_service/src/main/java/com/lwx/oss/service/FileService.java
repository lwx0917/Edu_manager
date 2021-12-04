package com.lwx.oss.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadAvatar(MultipartFile file);

    String uploadVideo(MultipartFile file);

    String getVideoById(String id) throws ClientException;
}
