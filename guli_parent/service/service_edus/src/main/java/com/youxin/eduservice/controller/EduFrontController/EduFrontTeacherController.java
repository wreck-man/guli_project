package com.youxin.eduservice.controller.EduFrontController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.EduCourse;
import com.youxin.eduservice.entity.EduTeacher;
import com.youxin.eduservice.service.EduCourseService;
import com.youxin.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/frontTeacher")
public class EduFrontTeacherController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService eduCourseService;
    @PostMapping("/pageList/{page}/{limit}")
    public Result pageList(@PathVariable Long page,@PathVariable Long limit){

        Map<String,Object> map=teacherService.pageMapWeb(page,limit);
        return Result.ok().data("map",map);
    }
    @PostMapping("/selectOne/{id}")
    public Result teacherInfo(@PathVariable String id){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("teacher_id",id);
        EduTeacher eduTeacher=teacherService.getById(id);
        List<EduCourse> eduCourses= eduCourseService.list(wrapper);
        return Result.ok().data("teacher",eduTeacher).data("course",eduCourses);
    }

}
