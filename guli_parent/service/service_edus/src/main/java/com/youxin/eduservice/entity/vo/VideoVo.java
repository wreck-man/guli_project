package com.youxin.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {
    private String id;
    private String title;
    private Integer isFree;
    @ApiModelProperty(value = "视频资源")
    private String videoSourceId;
}
