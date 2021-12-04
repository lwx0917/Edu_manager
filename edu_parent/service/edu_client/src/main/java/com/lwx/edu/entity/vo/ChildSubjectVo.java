package com.lwx.edu.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChildSubjectVo {
    private String id;
    private String title;
    private List<CourseInfoVo> children;
}
