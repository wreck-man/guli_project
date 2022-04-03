package com.youxin.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.commonutils.UcenterMember;
import com.youxin.commonutils.utils.JwtUtils;
import com.youxin.eduservice.client.EduUcenterClent;
import com.youxin.eduservice.entity.EduComment;
import com.youxin.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */

@RestController
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {
    @Autowired
    private EduCommentService eduCommentService;
    @GetMapping("/{page}/{limit}/{courseId}")
    public Result commentPages(@PathVariable Long page, @PathVariable Long limit, @PathVariable String courseId){
        Page page1=new Page(page,limit);
        Map<String,Object> map=eduCommentService.commentPages(page1,courseId);
        return Result.ok().data("map",map);
    }
    @PostMapping("/addComment")
    public Result addComment(@RequestBody(required = false) EduComment eduComment, HttpServletRequest request){
        String token= JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(token)){

            return Result.error().message("請先登錄").code(28004);

        }
        eduCommentService.addComment(eduComment,request);
        return Result.ok();
    }






}

