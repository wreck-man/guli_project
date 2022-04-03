package com.youxin.eduservice.mapper;

import com.youxin.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youxin.eduservice.entity.vo.CoursePublishVo;
import com.youxin.eduservice.entity.vo.CourseWebVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */

public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo publish(String id);
    CourseWebVo selectCourseById(String courseId);

}
