package com.youxin.excel.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelSubject {
    @ExcelProperty(index = 0)
    private String SubjectOne;
    @ExcelProperty(index = 1)
    private String SubjectTwo;

    public String getSubjectOne() {
        return SubjectOne;
    }

    public void setSubjectOne(String subjectOne) {
        SubjectOne = subjectOne;
    }

    public String getSubjectTwo() {
        return SubjectTwo;
    }

    public void setSubjectTwo(String subjectTwo) {
        SubjectTwo = subjectTwo;
    }
}
