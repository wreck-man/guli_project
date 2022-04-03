package com.youxin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youxin.eduservice.entity.vo.CoursePublishVo;
import com.youxin.eduservice.entity.vo.CourseVo;
import com.youxin.eduservice.entity.vo.CourseWebVo;
import com.youxin.eduservice.entity.vo.EduCourseVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
public interface EduCourseService extends IService<EduCourse> {
    Map<String, Object> courseFrontPage(Page page, CourseVo courseVo);

    String addCourse(EduCourseVo eduCourseVo);

    String updateCourse(EduCourseVo eduCourseVo);

    EduCourseVo getInfo(String id);

    CoursePublishVo publish(String id);

    boolean publishCourse(String id);

    Result coursePage(Page page, CourseVo courseVo);

    boolean removeCourseById(String id);
    CourseWebVo selectCourseById(String courseId);
    int updatePageViewCount(String id);
}
