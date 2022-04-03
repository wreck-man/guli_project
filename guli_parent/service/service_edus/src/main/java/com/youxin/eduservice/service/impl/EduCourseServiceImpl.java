package com.youxin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.client.VodClent;
import com.youxin.eduservice.entity.EduCourse;
import com.youxin.eduservice.entity.EduCourseDescription;
import com.youxin.eduservice.entity.EduTeacher;
import com.youxin.eduservice.entity.EduVideo;
import com.youxin.eduservice.entity.enums.CourseStatus;
import com.youxin.eduservice.entity.vo.CoursePublishVo;
import com.youxin.eduservice.entity.vo.CourseVo;
import com.youxin.eduservice.entity.vo.CourseWebVo;
import com.youxin.eduservice.entity.vo.EduCourseVo;
import com.youxin.eduservice.mapper.EduCourseMapper;
import com.youxin.eduservice.service.EduChapterService;
import com.youxin.eduservice.service.EduCourseDescriptionService;
import com.youxin.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxin.eduservice.service.EduVideoService;
import com.youxin.servicebase.exception.YouXinException;


import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private VodClent vodClent;
    private EduCourseVo eduCourseVo;
    @Override
    public String addCourse(EduCourseVo eduCourseVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseVo, eduCourse);
        int n = baseMapper.insert(eduCourse);
        if (n < 0) {
            throw new YouXinException(20001, "课程信息保存失败");
        }
        String id = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(eduCourseVo.getDescription());
        eduCourseDescription.setId(id);
        boolean f = eduCourseDescriptionService.save(eduCourseDescription);
        if (!f) {
            throw new YouXinException(20001, "课程详情信息保存失败");
        }
        return id;
    }

    @Override
    public String updateCourse(EduCourseVo eduCourseVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseVo, eduCourse);
        System.out.println(eduCourse);
        int n = baseMapper.updateById(eduCourse);
        if (n < 0) {
            throw new YouXinException(20001, "课程信息保存失败");
        }
        String id = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(eduCourseVo.getDescription());
        eduCourseDescription.setId(id);
        boolean f = eduCourseDescriptionService.updateById(eduCourseDescription);
        if (!f) {
            throw new YouXinException(20001, "课程详情信息保存失败");
        }
        return id;
    }

    @Override
    public EduCourseVo getInfo(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);

        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        if (eduCourse == null) {
            throw new YouXinException(20001, "没有该课程信息");
        }
        EduCourseVo eduCourseVo = new EduCourseVo();
        BeanUtils.copyProperties(eduCourse, eduCourseVo);
        if (eduCourseDescription!=null){
            eduCourseVo.setDescription(eduCourseDescription.getDescription());
        }


        return eduCourseVo;
    }

    @Override
    public CoursePublishVo publish(String id) {
        CoursePublishVo coursePublishVo=baseMapper.publish(id);

        return coursePublishVo;
    }

    @Override
    public boolean publishCourse(String id) {
        EduCourse course=new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        int i=baseMapper.updateById(course);
        return i>0;
    }

    @Override
    public Result coursePage(Page page, CourseVo courseVo) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("gmt_create");
        String title=courseVo.getTitle();
        String teacherId=courseVo.getTeacherId();
        String subjectParentId=courseVo.getSubjectParentId();
        String subjectId=courseVo.getSubjectId();
        if (title!=null&&!("".equals(title))){
            queryWrapper.like("title",title);
        }
        if (teacherId!=null&&!("".equals(teacherId))){
            queryWrapper.eq("teacher_id",teacherId);
        }
        if (subjectParentId!=null&&!("".equals(subjectParentId))){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if (subjectId!=null&&!("".equals(subjectId))){
            queryWrapper.eq("subject_id",subjectId);
        }
        baseMapper.selectPage(page,queryWrapper);
        long total= Math.toIntExact(page.getTotal());
        List<CourseVo> rows=page.getRecords();
        return Result.ok().data("rows",rows).data("total",total);


    }

    public Map<String, Object> courseFrontPage(Page page, CourseVo courseVo) {
        Page page1=page;
        QueryWrapper wrapper=new QueryWrapper();
        if (courseVo!=null){
            if (courseVo.getBuyCountSort()!=null&&!"".equals(courseVo.getBuyCountSort())){
                wrapper.orderByDesc("buy_count");
            }
            if (courseVo.getPriceSort()!=null&&!"".equals(courseVo.getPriceSort())){
                wrapper.orderByDesc("price");
            }
            if (courseVo.getGmtCreateSort()!=null&&!"".equals(courseVo.getGmtCreateSort())){
                wrapper.orderByDesc("gmt_create");
            }
            if (courseVo.getBuyCountSort()!=null&&!"".equals(courseVo.getBuyCountSort())){
                wrapper.orderByDesc("buy_count");
            }
            if (courseVo.getSubjectParentId()!=null&&!("".equals(courseVo.getSubjectParentId()))){
                wrapper.eq("subject_parent_id",courseVo.getSubjectParentId());
            }
            if (courseVo.getSubjectId()!=null&&!("".equals(courseVo.getSubjectId()))){
                wrapper.eq("subject_id",courseVo.getSubjectId());
            }
        }
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



    @Override
    public boolean removeCourseById(String id) {
        EduCourse eduCourse=baseMapper.selectById(id);
        if (null==eduCourse){
            throw new YouXinException(20001,"課程信息異常，請找管理員維護");
        }
        QueryWrapper wrapper=new QueryWrapper();
        String courseId=eduCourse.getId();
        wrapper.eq("id",courseId);
        int n=eduCourseDescriptionService.count(wrapper);
        boolean flag=false;
        if (n>0){
             flag=eduCourseDescriptionService.removeById(courseId);
        }
        wrapper=new QueryWrapper();
        wrapper.eq("course_id",courseId);
        wrapper.select("video_source_id");
        List<EduVideo> videoList=eduVideoService.list(wrapper);
        Result result=eduVideoService.removeVodeos(videoList);
        if (!result.getSuccess()){
            throw new YouXinException(20001,result.getMessage());
        }
        wrapper=new QueryWrapper();
        wrapper.eq("course_id",courseId);
        flag=eduVideoService.remove(wrapper);
        if (!flag){
            throw new YouXinException(20001,"刪除失敗");
        }
        flag=eduChapterService.remove(wrapper);
        int f=baseMapper.deleteById(id);
        flag=f>0;
        return flag;
    }

    @Override
    public CourseWebVo selectCourseById(String courseId) {
        int n=updatePageViewCount(courseId);
        CourseWebVo courseWebVo=baseMapper.selectCourseById(courseId);
        return courseWebVo;
    }

    @Override
    public int updatePageViewCount(String id) {
        System.out.println(id);
        EduCourse eduCourse=baseMapper.selectById(id);
        eduCourse.setBuyCount(eduCourse.getBuyCount()+1);
        int n=baseMapper.updateById(eduCourse);
        return n;
    }

    public  void test(String id) {
        EduCourseServiceImpl e=new EduCourseServiceImpl();
        e.vodClent.remove("1");
    }
}
