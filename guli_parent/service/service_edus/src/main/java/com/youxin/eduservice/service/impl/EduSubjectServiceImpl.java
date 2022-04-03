package com.youxin.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.youxin.eduservice.entity.EduSubject;
import com.youxin.eduservice.entity.vo.EduOneSubject;
import com.youxin.eduservice.entity.vo.EduTwoSubject;
import com.youxin.eduservice.entity.vo.SubjectVo;
import com.youxin.eduservice.listeners.EazyExcelListener;
import com.youxin.eduservice.mapper.EduSubjectMapper;
import com.youxin.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-09
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void read(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream stream=file.getInputStream();
            EasyExcel.read(stream, SubjectVo.class,new EazyExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public List<EduOneSubject> getSubjectList() {
        List<EduSubject> totalList=baseMapper.selectList(null);
        List<EduOneSubject> result=new ArrayList<>();
        for (EduSubject eduSubject: totalList){
            if (eduSubject.getParentId().equals("0")){
                EduOneSubject eduOneSubject=new EduOneSubject();
                BeanUtils.copyProperties(eduSubject,eduOneSubject);
                for (EduSubject eduSubject1: totalList){
                    if (eduSubject.getId().equals(eduSubject1.getParentId())){
                        EduTwoSubject eduTwoSubject=new EduTwoSubject();
                        BeanUtils.copyProperties(eduSubject1,eduTwoSubject);
                        eduOneSubject.getChildren().add(eduTwoSubject);
                    }
                }
                result.add(eduOneSubject);
            }

        }
        System.out.println(result);
        return result;
    }
}
