package com.youxin.excel.test;

import com.alibaba.excel.annotation.ExcelProperty;

public class domo {
    @ExcelProperty(index = 0)
    private Integer id;
    @ExcelProperty(index = 1)
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
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
