package com.youxin.vods.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.youxin.vods.utils.VodParamsUtil;

import static com.youxin.vods.utils.UploadUtils.initVodClient;

/**
 *
 * 音频或视频获取上传凭证的示例
 */
 class test1 {



    /*获取播放凭证函数*/
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("视频ID");
        return client.getAcsResponse(request);
    }

    /*以下为调用示例*/
    public static void main(String[] argv) throws ClientException {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("0b4a8bfb81af4136bb9374ef26f8db8a");
        DefaultAcsClient client = initVodClient(VodParamsUtil.ACCESS_KEYS, VodParamsUtil.ACCESS_KEY_SECRET);
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = client.getAcsResponse(request);;
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}