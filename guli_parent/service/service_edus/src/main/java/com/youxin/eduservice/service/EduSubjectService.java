package com.youxin.eduservice.service;

import com.youxin.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youxin.eduservice.entity.vo.EduOneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-09
 */
public interface EduSubjectService extends IService<EduSubject> {
    void read(MultipartFile file, EduSubjectService eduTeacherService);

    List<EduOneSubject> getSubjectList();
}
