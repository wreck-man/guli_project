package com.youxin.eduservice.client;

import com.youxin.commonutils.Result;

import com.youxin.eduservice.client.impl.VodClentImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
@FeignClient(name = "service-vod",fallback = VodClentImpl.class)
@Component
public interface VodClent {
    @PostMapping("/eduservice/edu-vod/upload")
    public Result upload(MultipartFile file);
    @PostMapping("/eduservice/edu-vod/remove/{id}")
    public Result remove(@PathVariable(name = "id") String id) ;
}
