package com.youxin.eduservicecms.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;
@Data
public class CrmBannerVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
