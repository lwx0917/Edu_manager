package com.lwx.edu.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryVo {
    private String title;
    private String cover;
    private String courseId;
    private String chapterId;
    private String userId;
    private String videoId;
    private Date nowTime;
    private String chapter;
    private String video;
    private String second;
    private Date now;

//    select c.title,
//    c.cover,
//    c.id    as course_id,
//    h.user_id,
//    h.now_time,
//    h.chapter_id,
//    h.video_id,
//    s.title as chapter,
//    v.title as video
}
