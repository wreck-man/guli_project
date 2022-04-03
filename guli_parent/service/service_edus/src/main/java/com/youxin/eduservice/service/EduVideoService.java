package com.youxin.eduservice.service;

import com.youxin.commonutils.Result;
import com.youxin.eduservice.entity.EduChapter;
import com.youxin.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youxin.eduservice.entity.vo.VideoInfoForm;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
public interface EduVideoService extends IService<EduVideo> {
    Result removeVodeos(List<EduVideo> videoList);
    boolean removeVideo(String id);

    VideoInfoForm getOneById(String id);

    boolean updateByVideoId(String id, VideoInfoForm videoInfoForm);

    boolean saveVideo(VideoInfoForm videoInfoForm);
}
