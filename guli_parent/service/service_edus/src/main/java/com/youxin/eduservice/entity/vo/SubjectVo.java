package com.youxin.eduservice.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectVo {
    @ExcelProperty(index = 0)
    private String subjectOne;
    @ExcelProperty(index = 1)
    private String subjectTwo;
}
