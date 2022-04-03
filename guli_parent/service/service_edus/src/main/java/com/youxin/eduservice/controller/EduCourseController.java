package com.youxin.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.client.VodClent;
import com.youxin.eduservice.entity.vo.CoursePublishVo;
import com.youxin.eduservice.entity.vo.CourseVo;
import com.youxin.eduservice.entity.vo.EduCourseVo;
import com.youxin.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */

@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private VodClent vodClent;
    @PostMapping("/add")
    public Result addCourse(@RequestBody EduCourseVo eduCourseVo) {
        String id = eduCourseService.addCourse(eduCourseVo);
        return Result.ok().data("id", id);
    }

    @PostMapping("/update")
    public Result updateCourse(@RequestBody EduCourseVo eduCourseVo) {
        String id = eduCourseService.updateCourse(eduCourseVo);
        return Result.ok().data("id", id);
    }

    @GetMapping("/getInfo/{id}")
    public Result getInfo(@PathVariable String id) {
        System.out.println(id);
        EduCourseVo eduCourseVo=eduCourseService.getInfo(id);
        return Result.ok().data("courseInfo",eduCourseVo);
    }
    @PostMapping("/getCourse/{id}")
    public Result getCoursePublishVoById(@PathVariable String id){
        CoursePublishVo coursePublishVo=eduCourseService.publish(id);
        return Result.ok().data("item",coursePublishVo);

    }
    @PostMapping("/publish/{id}")
    public Result publish(@PathVariable String id){
        boolean flag=eduCourseService.publishCourse(id);
        if (!flag){

            return Result.ok().message("发布失败");
        }
        return Result.ok().message("发布成功");

    }
    @GetMapping("/page/list/{page}/{limit}")
    public Result coursePage(@PathVariable long page, @PathVariable long limit,  CourseVo courseVo){
        Page page1=new Page(page,limit);
        Result result=eduCourseService.coursePage(page1,courseVo);
        return result;
    }
    @PostMapping("/deleteCourse/{id}")
    public Result deleteCourse(@PathVariable String id){
        boolean n=eduCourseService.removeCourseById(id);
        if (!n){
            return Result.error().message("刪除失敗");
        }
        return Result.ok().message("刪除成功");







    }
    @PostMapping("test")
    public void test(){
        vodClent.remove("1");
    }
}

