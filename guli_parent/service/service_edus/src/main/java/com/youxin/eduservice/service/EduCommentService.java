package com.youxin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> commentPages(Page page1, String courseId);

    void addComment(EduComment courseId, HttpServletRequest request);
}
