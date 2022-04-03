package com.youxin.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class EduOneSubject {
    private String id;
    private String title;
    private List<EduTwoSubject> children=new ArrayList<>();

}
