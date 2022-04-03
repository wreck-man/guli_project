package com.youxin.vods.controller;

import com.aliyuncs.exceptions.ClientException;


import com.youxin.vods.service.EduVodService;
import com.youxin.vods.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Controller
@RestController
@RequestMapping("/eduservice/edu-vod")
public class EduVodController {
    @Autowired
    private EduVodService eduVodService;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String url=eduVodService.vodUpload(file);
        return Result.ok().data("url",url);
    }
    @PostMapping("/remove/{id}")
    public Result remove(@PathVariable(name = "id") String id) {
        eduVodService.vodRemove(id);
        return Result.ok().message("刪除視頻成功");
    }
    @GetMapping("get-play-auth/{videoId}")
    public Result getPlayAuth(@PathVariable String videoId){
        String res= null;
        try {
            res = eduVodService.getAuthPlay(videoId);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return Result.ok().data("result",res);


    }


}
