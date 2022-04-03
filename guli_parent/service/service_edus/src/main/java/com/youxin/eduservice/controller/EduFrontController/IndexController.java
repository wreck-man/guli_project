package com.youxin.eduservice.controller.EduFrontController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.EduCourse;
import com.youxin.eduservice.entity.EduTeacher;
import com.youxin.eduservice.service.EduCourseService;
import com.youxin.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/index")
public class IndexController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;
    //查询前8条热门课程，查询前4条名师
    @GetMapping("/hot")
    public Result index() {
//查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);
//查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return Result.ok().data("eduList",eduList).data("teacherList",teacherList);
    }
}
