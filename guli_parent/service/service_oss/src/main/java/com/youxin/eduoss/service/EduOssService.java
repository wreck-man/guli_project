package com.youxin.eduoss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface EduOssService {
    public String upload(MultipartFile multipartFile);
}
