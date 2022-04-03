package com.youxin.eduservice.controller.EduFrontController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.commonutils.utils.JwtUtils;
import com.youxin.eduservice.client.OrderClent;
import com.youxin.eduservice.entity.vo.ChapterVo;
import com.youxin.eduservice.entity.vo.CourseVo;
import com.youxin.eduservice.entity.vo.CourseWebVo;
import com.youxin.eduservice.entity.vo.EduCourseVo;
import com.youxin.eduservice.service.EduChapterService;
import com.youxin.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/frontCourse")
public class EduFrontCourseController {
    @Autowired
    private OrderClent orderClent;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduCourseService eduCourseService;
    @PostMapping("/pageList/{page}/{limit}")
    public Result pageList(@PathVariable Long page,@PathVariable Long limit,@RequestBody(required = false) CourseVo eduCourseVo){
        Page page1=new Page(page,limit);
        Map<String ,Object> map=eduCourseService.courseFrontPage(page1,eduCourseVo);
        return Result.ok().data("map",map);
    }
    @PostMapping("/webCourseInfo/{id}")
    public Result webCourseInfo(@PathVariable String id, HttpServletRequest request){
        String memberId= JwtUtils.getMemberIdByJwtToken(request);
        boolean flag=orderClent.isBuyCourse(memberId,id);
        CourseWebVo courseWebVo=eduCourseService.selectCourseById(id);
        List<ChapterVo> list=eduChapterService.ChapterAndVideo(id);
        return Result.ok().data("courseInfo",courseWebVo).data("chapterList",list).data("isbuy",flag);
    }
    @GetMapping("/webCourseInfo/{id}")
    public CourseWebVo webCourseVo(@PathVariable String id){
        CourseWebVo courseWebVo=eduCourseService.selectCourseById(id);
        return courseWebVo;
    }
}
