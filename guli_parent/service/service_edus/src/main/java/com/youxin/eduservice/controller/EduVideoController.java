package com.youxin.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.EduChapter;
import com.youxin.eduservice.entity.EduVideo;
import com.youxin.eduservice.entity.vo.ChapterVo;
import com.youxin.eduservice.entity.vo.VideoInfoForm;
import com.youxin.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping("/addVideo")
    public Result addChapter(@RequestBody VideoInfoForm videoInfoForm) {
        boolean n = eduVideoService.saveVideo(videoInfoForm);
        if (!n) {
            return Result.error();
        }

        return Result.ok();
    }

//    @PostMapping("ChapterAndVideo/{courseId}")
//    public Result ChapterAndVideo(@PathVariable String courseId) {
//        List<ChapterVo> n = eduVideoService.ChapterAndVideo(courseId);
//        return Result.ok().data("list", n);
//    }

    @PostMapping("/updateVideo/{id}")
    public Result update(@PathVariable String id, @RequestBody VideoInfoForm videoInfoForm) {
        boolean n = eduVideoService.updateByVideoId(id,videoInfoForm);
        if (!n) {
            return Result.error().message("更新失败");
        }

        return Result.ok();
    }

    @DeleteMapping("/deleteVideo/{id}")
    public Result delete(@PathVariable String id) {
        boolean n = eduVideoService.removeVideo(id);
        if (n) {
            return Result.ok();
        }
        return Result.error().message("删除失败");
    }

    @GetMapping("/video-info/{id}")
    public Result getById(@PathVariable String id) {
        VideoInfoForm n = eduVideoService.getOneById(id);
        return Result.ok().data("item", n);
    }

}

