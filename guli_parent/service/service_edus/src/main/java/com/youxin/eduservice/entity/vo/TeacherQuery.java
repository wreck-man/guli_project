package com.youxin.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer level;
    private Date begin;
    private Date end;
}
