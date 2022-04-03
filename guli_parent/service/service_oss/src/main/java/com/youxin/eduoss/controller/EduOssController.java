package com.youxin.eduoss.controller;

import com.youxin.commonutils.Result;
import com.youxin.eduoss.service.EduOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Action;


@RestController
@RequestMapping("/eduservice/edu-oss/file")
public class EduOssController {
    @Autowired
    private EduOssService eduOssService;
    @PostMapping("/upload")
    public Result upload(@RequestParam("file")MultipartFile multipartFile){
        String url=eduOssService.upload(multipartFile);
        return Result.ok().data("url",url).message("文件上传成功");
    }
}
