package com.youxin.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.EduChapter;
import com.youxin.eduservice.entity.vo.ChapterVo;
import com.youxin.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */

@RestController
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter) {
        boolean n = eduChapterService.save(eduChapter);
        if (!n) {
            return Result.error();
        }

        return Result.ok();
    }

    @PostMapping("ChapterAndVideo/{courseId}")
    public Result ChapterAndVideo(@PathVariable String courseId) {
        List<ChapterVo> n = eduChapterService.ChapterAndVideo(courseId);
        return Result.ok().data("list", n);
    }

    @PostMapping("/updateChapter/{id}")
    public Result update(@PathVariable String id, @RequestBody EduChapter eduChapter) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        boolean n = eduChapterService.update(eduChapter, wrapper);
        if (!n) {
            return Result.error();
        }

        return Result.ok();
    }

    @DeleteMapping("/deleteChapter/{id}")
    public Result delete(@PathVariable String id) {
        boolean n = eduChapterService.removeChacter(id);
        if (n) {
            return Result.ok();
        }
        return Result.error().message("删除失败");
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        EduChapter n = eduChapterService.getOne(wrapper);
        return Result.ok().data("item", n);
    }
}

