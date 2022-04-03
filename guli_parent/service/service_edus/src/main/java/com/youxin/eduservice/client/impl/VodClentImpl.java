package com.youxin.eduservice.client.impl;

import com.youxin.commonutils.Result;
import com.youxin.eduservice.client.VodClent;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class VodClentImpl implements VodClent {
    @Override
    public Result upload(MultipartFile file) {
        return Result.error().message("系統繁忙，稍後再試。");
    }

    @Override
    public Result remove(String id) {
        return Result.error().message("系統繁忙，稍後再試。");
    }
}
