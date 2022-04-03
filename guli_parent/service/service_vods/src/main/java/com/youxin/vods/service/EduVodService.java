package com.youxin.vods.service;

import com.aliyuncs.exceptions.ClientException;
import io.netty.util.internal.MacAddressUtil;
import org.springframework.web.multipart.MultipartFile;

public interface EduVodService {
    String vodUpload(MultipartFile file);
    String getAuthPlay(String videoid) throws ClientException;
    void vodRemove(String id);
}
