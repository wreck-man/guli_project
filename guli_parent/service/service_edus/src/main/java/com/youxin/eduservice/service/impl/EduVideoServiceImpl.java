package com.youxin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.youxin.commonutils.Result;
import com.youxin.eduservice.client.VodClent;
import com.youxin.eduservice.entity.EduChapter;
import com.youxin.eduservice.entity.EduVideo;
import com.youxin.eduservice.entity.vo.VideoInfoForm;
import com.youxin.eduservice.mapper.EduVideoMapper;
import com.youxin.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxin.servicebase.exception.YouXinException;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClent vodClent;
    @Override
    public boolean removeVideo(String id) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.select("video_source_id");
        wrapper.eq("id",id);
        EduVideo eduVideo=baseMapper.selectOne(wrapper);
        if (!StringUtils.isEmpty(eduVideo)){
            Result result=vodClent.remove(eduVideo.getVideoSourceId());
            if (!result.getSuccess()){
                throw new YouXinException(20001,"刪除小節失敗");
            }
        }
        int flag=baseMapper.deleteById(id);
        return flag>0;
    }

    public Result removeVodeos(List<EduVideo> videoList) {
        if (videoList.isEmpty()){
            return Result.ok();
        }
        List<String> videoIdStr=new ArrayList<>();
        for (EduVideo cur:videoList){
            videoIdStr.add(cur.getVideoSourceId());
        }
        String resVo= StringUtil.join(videoIdStr.toArray(),",");
        QueryWrapper wrapper=new QueryWrapper();
        Result result=vodClent.remove(resVo);

        return result;
    }

    @Override
    public VideoInfoForm getOneById(String id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        EduVideo eduVideo=baseMapper.selectById(id);
        if(eduVideo == null){
            throw new YouXinException(20001, "数据不存在");
        }
        VideoInfoForm videoInfoForm=new VideoInfoForm();
        BeanUtils.copyProperties(eduVideo,videoInfoForm);
        return videoInfoForm;
    }

    @Override
    public boolean updateByVideoId(String id, VideoInfoForm videoInfoForm) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("id",id);
        EduVideo video=new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,video);
        int flag=baseMapper.updateById(video);
        return flag>0;
    }

    @Override
    public boolean saveVideo(VideoInfoForm videoInfoForm) {
        EduVideo video=new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,video);
        int i=baseMapper.insert(video);
        return i>0;
    }
}
