package com.lwx.edu.entity.vo;

import com.lwx.edu.entity.Video;
import lombok.Data;

import java.util.List;

@Data
public class ChapterList {

    private String id;
    private String courseId;
    private String title;
    private List<Video> videos;
}
