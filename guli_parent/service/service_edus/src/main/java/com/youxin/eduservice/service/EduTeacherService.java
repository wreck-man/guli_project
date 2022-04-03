package com.youxin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youxin.eduservice.entity.vo.TeacherQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-02-27
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void PageQuery(Page page, TeacherQuery teacherQuery);


    Map<String, Object> pageMapWeb(long p,long l);
}
