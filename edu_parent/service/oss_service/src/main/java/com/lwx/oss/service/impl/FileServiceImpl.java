package com.lwx.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.vod.upload.UploadVideo;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.lwx.oss.service.FileService;
import com.lwx.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadAvatar(MultipartFile file) {
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String url = null;
        try {
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String random = UUID.randomUUID().toString();
            String currentDate = new DateTime().toString("yyyy/MM/dd");
            String fileName = file.getOriginalFilename();
            fileName = currentDate + "/" + random + fileName;
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
            //https://my-edu-demo.oss-cn-shenzhen.aliyuncs.com/3.jpeg
            url = "https://" + bucketName + "." + endPoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        UploadStreamRequest request = null;
        try {
            /**
             * title : ???????????????????????????
             * fileName: ????????????????????????
             * inputStream : ?????????????????????
             */
            String fileName = file.getOriginalFilename();
            //title????????????????????????????????????
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            request = new UploadStreamRequest(ConstantPropertiesUtil.KEY_ID, ConstantPropertiesUtil.KEY_SECRET, title, fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //??????????????????????????????????????????ID
        String videoId = null;
        //???????????????????????????
        UploadVideo uploader = new UploadVideoImpl();
        //????????????????????????????????????????????????
        UploadStreamResponse response = uploader.uploadStream(request);
        //??????????????????????????????????????????????????????videoId???
        //??????????????????videoId?????????????????????????????????????????????
//        System.out.println("RequestId=" + response.getRequestId());
        if (response.isSuccess()) {
            videoId = response.getVideoId();
//            System.out.println("VideoId=" + response.getVideoId());
        } else {
//            System.out.println("videoId=" + response.getVideoId());
//            System.out.println("ErrorCode=" + response.getCode());
//            System.out.println("ErrorMessage=" + response.getMessage());
            return response.getMessage();
        }
        return videoId;
    }

    @Override
    public String getVideoById(String id) throws ClientException {
        String url = "";
        String regionId = "cn-shanghai";  // ????????????????????????
        DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstantPropertiesUtil.KEY_ID, ConstantPropertiesUtil.KEY_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(id);
        GetPlayInfoResponse response = client.getAcsResponse(request);
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //????????????
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            url = playInfo.getPlayURL();
//            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base??????
//        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        return url;
    }

}
