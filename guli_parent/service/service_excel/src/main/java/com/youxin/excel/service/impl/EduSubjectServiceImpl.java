package com.youxin.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.youxin.excel.entity.EduSubject;
import com.youxin.excel.entity.vo.ExcelSubject;
import com.youxin.excel.listeners.easyExcelListener;
import com.youxin.excel.mapper.EduSubjectMapper;
import com.youxin.excel.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

//    @Override
//    public boolean insertSubject(MultipartFile file,EduSubjectService eduSubjectService) {
//        try {
//            InputStream inputStream=file.getInputStream();
//            EasyExcel.read(inputStream,new easyExcelListener()).sheet().doRead();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
}
