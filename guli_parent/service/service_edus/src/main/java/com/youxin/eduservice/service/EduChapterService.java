package com.youxin.eduservice.service;

import com.youxin.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youxin.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> ChapterAndVideo(String courseId);

    boolean removeChacter(String id);
}
