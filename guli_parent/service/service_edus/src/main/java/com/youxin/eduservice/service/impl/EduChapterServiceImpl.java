package com.youxin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.eduservice.entity.EduChapter;
import com.youxin.eduservice.entity.EduVideo;
import com.youxin.eduservice.entity.vo.ChapterVo;
import com.youxin.eduservice.entity.vo.VideoVo;
import com.youxin.eduservice.mapper.EduChapterMapper;
import com.youxin.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxin.eduservice.service.EduVideoService;
import com.youxin.servicebase.exception.YouXinException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> ChapterAndVideo(String courseId) {
        List<ChapterVo> result=new ArrayList<>();
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters=baseMapper.selectList(queryWrapper);
        if (eduChapters!=null){
            List<EduVideo> eduVideos=eduVideoService.list(queryWrapper);
            for (int i=0;i<eduChapters.size();i++){
                ChapterVo chapterVo=new ChapterVo();
                BeanUtils.copyProperties(eduChapters.get(i),chapterVo);
                for (int j=0;j<eduVideos.size();j++){
                 if (eduVideos.get(j).getChapterId().equals(eduChapters.get(i).getId())){
                     VideoVo videoVo=new VideoVo();
                     BeanUtils.copyProperties(eduVideos.get(j),videoVo);
                     chapterVo.getChildren().add(videoVo);
                 }
                }
                result.add(chapterVo);
            }
        }
        return result;
    }

    @Override
    public boolean removeChacter(String id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("chapter_id",id);
        List<EduVideo> n=eduVideoService.list(queryWrapper);
        if (!StringUtils.isEmpty(n)){
            for (EduVideo cur:n){
                boolean f=eduVideoService.removeVideo(cur.getId());
                if (!f){
                    throw new YouXinException(20001,"刪除失敗");
                }

            }
        }
        int result=baseMapper.deleteById(id);

        return result>0;
    }
}
