package com.youxin.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.EduTeacher;
import com.youxin.eduservice.entity.vo.TeacherQuery;
import com.youxin.eduservice.service.EduTeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-27
 */

@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @PostMapping("/login")
    public Result login(){
        System.out.println("login");
        return Result.ok().data("token","admin");
    }
    @PostMapping("/all")
    public Result all(){
        List<EduTeacher> list=eduTeacherService.list(null);
        return Result.ok().data("list",list);
    }
    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("roles","[admin]").data("name","admin");
    }
    @GetMapping
    public Result list(){

        List list= eduTeacherService.list(null);
        return Result.ok().data("item",list);
    }
    @DeleteMapping("{id}")
    public Result removeByid(@PathVariable String id){

        boolean n= eduTeacherService.removeById(id);
        if (n){
            return Result.ok();
        }
        return Result.error();
    }
    @PostMapping("{page}/{limit}")
    public Result pageList(@PathVariable Long page, @PathVariable Long limit, @RequestBody TeacherQuery teacherQuery){
        Page page1=new Page(page,limit);
        eduTeacherService.PageQuery(page1,teacherQuery);
        long total=page1.getTotal();
        List list=page1.getRecords();
        return Result.ok().data("total",total).data("rows",list);
    }
    @PostMapping("save")
    public Result save(@RequestBody EduTeacher teacher){
        boolean n=eduTeacherService.save(teacher);
        if (n){
            return Result.ok();
        }
        return Result.error();
    }
    @GetMapping("{id}")
    public Result selectId(@PathVariable String id){
        EduTeacher teacher=eduTeacherService.getById(id);
        return Result.ok().data("item",teacher);
    }
    @PostMapping("{id}")
    public Result updateId(@PathVariable String id,@RequestBody EduTeacher teacher){
        teacher.setId(id);
        boolean n=eduTeacherService.updateById(teacher);
        if (n){
            return Result.ok();
        }
        return Result.error();
    }

}

