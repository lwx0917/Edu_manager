package com.lwx.edu.entity.vo;
import lombok.Data;

@Data
public class NewsVo {

    private Integer id;
    private String topic;
    private String name;
    private String title;
    private String cover;
    private String content;
    private String createTime;
}
