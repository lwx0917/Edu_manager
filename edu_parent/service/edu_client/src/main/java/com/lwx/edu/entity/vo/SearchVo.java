package com.lwx.edu.entity.vo;

import lombok.Data;

@Data
public class SearchVo {
    private String id;
    private String title;
    private Integer lessonNum;
    private String cover;
    private String teacherName;
    private Integer viewCount;
    private String description;
}
