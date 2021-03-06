package com.youxin.excel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.youxin.commonutils.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author testjava
 * @since 2022-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduSubject对象", description="课程科目")
public class EduSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程类别ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


    /**
     * <p>
     * 课程科目 前端控制器
     * </p>
     *
     * @author testjava
     * @since 2022-03-08
     */
    @RestController
    @RequestMapping("/excel/edu-subject")
    public static class EduSubjectController {
    //    @Autowired
    //    private EduSubjectService eduSubjectService;
        @RequestMapping("/insert-subject")
        public Result insertSubject(MultipartFile file){
            boolean flag=false;
    //        flag=eduSubjectService.insertSubject(file,eduSubjectService);
            if (flag){
                return Result.ok().message("導入成功");
            }
            return Result.error().message("導入失敗");




        }

    }
}
