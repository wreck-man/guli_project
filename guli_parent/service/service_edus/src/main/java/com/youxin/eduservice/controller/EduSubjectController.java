package com.youxin.eduservice.controller;


import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.vo.EduOneSubject;
import com.youxin.eduservice.service.EduSubjectService;
import com.youxin.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    @PostMapping("/upload")
    public Result read(MultipartFile file){

        eduSubjectService.read(file,eduSubjectService);

        return Result.ok().message("上傳成功");
    }
    @PostMapping("/list")
    public Result getList(){
    List<EduOneSubject> list=eduSubjectService.getSubjectList();
    return Result.ok().data("list",list);
    }




}

