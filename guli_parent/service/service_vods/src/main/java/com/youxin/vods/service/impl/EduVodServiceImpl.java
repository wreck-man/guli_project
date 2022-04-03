package com.youxin.vods.service.impl;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import com.youxin.vods.exception.YouXinException;
import com.youxin.vods.service.EduVodService;
import com.youxin.vods.utils.VodParamsUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.youxin.vods.utils.UploadUtils.initVodClient;

@Service
public class EduVodServiceImpl implements EduVodService {
    private static String testUploadStream(String accessKeyId, String accessKeySecret, String title, String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return response.getVideoId();
    }

    public String getAuthPlay(String videoid) throws ClientException {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoid);
        DefaultAcsClient client = initVodClient(VodParamsUtil.ACCESS_KEYS, VodParamsUtil.ACCESS_KEY_SECRET);
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        String res="";
        try {
            response = client.getAcsResponse(request);;
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            res=response.getPlayAuth();
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
        return res;
    }
    @Override
    public String vodUpload(MultipartFile file) {
        //您的AccessKeyId
        String accessKeyId = VodParamsUtil.ACCESS_KEYS;
        //您的AccessKeySecret
        String accessKeySecret = VodParamsUtil.ACCESS_KEY_SECRET;
        System.out.println(accessKeyId);
        System.out.println(accessKeySecret);
        //需要上传到VOD的本地视频文件的完整路径，需要包含文件扩展名
        String fileName = file.getOriginalFilename();
        String title = file.getOriginalFilename();
        String videoId = null;
        try {
            videoId = testUploadStream(accessKeyId,accessKeySecret,title,fileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoId;

    }

    @Override
    public void vodRemove(String id) {
        System.out.println(id);
        if ((null==id)||("".equals(id))){
            return;
        }
        DefaultAcsClient client = null;
        try {
            client = initVodClient("LTAI5tCvVd9gRhYq3KQVczqX", "IRZrMvd7QV6VeeBo3rl8YXwmu4sATR");
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new YouXinException(20001,"刪除視頻失敗");
        }
        DeleteVideoResponse response = new DeleteVideoResponse();

        try {

            response = deleteVideo(client,id);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            throw  new YouXinException(20001,"刪除視頻失敗!");
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }


    /**
     * 删除视频
     * @param client 发送请求客户端
     * @return DeleteVideoResponse 删除视频响应数据
     * @throws Exception
     */
    public static DeleteVideoResponse deleteVideo(DefaultAcsClient client,String id) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(id);
        return client.getAcsResponse(request);
    }

    /*请求示例*/

}