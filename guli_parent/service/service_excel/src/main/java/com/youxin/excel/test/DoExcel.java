package com.youxin.excel.test;

import com.alibaba.excel.annotation.ExcelProperty;

public class DoExcel {
    @ExcelProperty("学生编号")
    private Integer id;
    @ExcelProperty("学生姓名")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DoExcel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
