package com.lwx.edu.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryVo {
    private String title;
    private String cover;
    private String courseId;
    private String subject;
    private String userId;
    private Date now;
}
