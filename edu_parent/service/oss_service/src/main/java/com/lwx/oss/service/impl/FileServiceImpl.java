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
             * title : 上传之后显示的名称
             * fileName: 上传文件原始名称
             * inputStream : 上传文件输入流
             */
            String fileName = file.getOriginalFilename();
            //title不让它带后缀名，个人建议
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            request = new UploadStreamRequest(ConstantPropertiesUtil.KEY_ID, ConstantPropertiesUtil.KEY_SECRET, title, fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //一般我们都需要保存上传视频的ID
        String videoId = null;
        //创建实现上传的对象
        UploadVideo uploader = new UploadVideoImpl();
        //上传动作，到这一步，已经上传成功
        UploadStreamResponse response = uploader.uploadStream(request);
        //下面是打印信息，用于测试，并且保存下videoId，
        //以便后面通过videoId获取到视频的播放地址或播放凭证
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
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstantPropertiesUtil.KEY_ID, ConstantPropertiesUtil.KEY_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(id);
        GetPlayInfoResponse response = client.getAcsResponse(request);
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            url = playInfo.getPlayURL();
//            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
//        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        return url;
    }

}
