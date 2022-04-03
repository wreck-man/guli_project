package com.youxin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.youxin.eduservice.entity.EduTeacher;
import com.youxin.eduservice.entity.vo.TeacherQuery;
import com.youxin.eduservice.mapper.EduTeacherMapper;
import com.youxin.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-02-27
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void PageQuery(Page page, TeacherQuery teacherQuery) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("gmt_create");
        if (teacherQuery==null){
            baseMapper.selectPage(page,wrapper);
            return;
        }
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        Date begin=teacherQuery.getBegin();
        Date end = teacherQuery.getEnd();
        if (name!=null){
            wrapper.like("name",name);
        }
        if (level!=null){
            wrapper.eq("level",level);
        }
        if (begin!=null){
            wrapper.ge("gmt_create",begin);
        }
        if (end!=null){
            wrapper.le("gmt_modified",end);
        }
        baseMapper.selectPage(page,wrapper);
    }

    @Override
    public Map<String, Object> pageMapWeb(long page,long limit) {
        Page page1=new Page(page,limit);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("sort");
        baseMapper.selectPage(page1,wrapper);
        List<EduTeacher> records=page1.getRecords();
        long total=page1.getTotal();
        long current=page1.getCurrent();
        boolean hasPrevious=page1.hasPrevious();
        boolean hasNext=page1.hasNext();
        long size=page1.getSize();
        long pages=page1.getPages();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("current",current);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);
        map.put("size",size);
        map.put("pages",pages);
        map.put("items",records);
        return map;
    }
}
