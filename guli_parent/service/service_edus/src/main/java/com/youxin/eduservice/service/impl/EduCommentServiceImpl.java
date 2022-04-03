package com.youxin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.commonutils.UcenterMember;
import com.youxin.commonutils.utils.JwtUtils;
import com.youxin.eduservice.client.EduUcenterClent;
import com.youxin.eduservice.entity.EduComment;
import com.youxin.eduservice.entity.EduCourse;
import com.youxin.eduservice.entity.EduTeacher;
import com.youxin.eduservice.mapper.EduCommentMapper;
import com.youxin.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxin.eduservice.service.EduCourseService;
import com.youxin.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
    @Autowired
    private EduUcenterClent eduUcenterClent;
    @Autowired
    private EduCourseService eduCourseService;
    @Override
    public Map<String, Object> commentPages(Page page1, String courseId) {
        Page page=page1;
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(page,wrapper);
        List<EduComment> commentList = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", page.getCurrent());
        map.put("pages", page.getPages());
        map.put("size", page.getSize());
        map.put("total", page.getTotal());
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());
        return map;
    }

    @Override
    public void addComment(EduComment eduComment, HttpServletRequest request) {
        String token= JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember ucenterMember=eduUcenterClent.getLoginInfo(token);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("id",eduComment.getCourseId());
        EduCourse eduCourse =eduCourseService.getOne(wrapper);
        eduComment.setTeacherId(eduCourse.getTeacherId());
        eduComment.setAvatar(ucenterMember.getAvatar());
        eduComment.setNickname(ucenterMember.getNickname());
        eduComment.setMemberId(ucenterMember.getId());
        baseMapper.insert(eduComment);
        System.out.println(eduComment);
    }
}
