package com.youxin.eduservice.listeners;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youxin.eduservice.entity.EduSubject;
import com.youxin.eduservice.entity.vo.SubjectVo;
import com.youxin.eduservice.service.EduSubjectService;

import java.sql.Wrapper;
import java.util.Map;

public class EazyExcelListener extends AnalysisEventListener<SubjectVo> {
    private EduSubjectService eduSubjectService;

    public EazyExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectVo o, AnalysisContext analysisContext) {
        QueryWrapper wrapper=new  QueryWrapper();
        wrapper.eq("title",o.getSubjectOne());
        EduSubject eduSubject=eduSubjectService.getOne(wrapper);
        if (eduSubject==null){
            eduSubject=new EduSubject();
            eduSubject.setTitle(o.getSubjectOne());
            eduSubject.setParentId("0");
            eduSubjectService.save(eduSubject);
        }
        String pid=eduSubject.getId();
        QueryWrapper wrapper1=new QueryWrapper();
        wrapper1.eq("title",o.getSubjectTwo());
        EduSubject eduSubject2=eduSubjectService.getOne(wrapper1);
        if (eduSubject2==null){
            eduSubject2=new EduSubject();
            eduSubject2.setTitle(o.getSubjectTwo());
            eduSubject2.setParentId(pid);
            eduSubjectService.save(eduSubject2);
        }
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        System.out.println("表頭信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
