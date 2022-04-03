package com.youxin.eduservice.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youxin.eduservice.entity.vo.TeacherQuery;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Wrapper;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-02-27
 */

public interface EduTeacherMapper extends BaseMapper<EduTeacher> {
}
