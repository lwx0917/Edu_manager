package com.lwx.edu.entity.vo;

import lombok.Data;

@Data
public class StageResultVo {
    private Integer id;
    private Integer categoryId;
    private String title;
    private String description;
    private String stage;
    private java.util.List<ListVo> lists;
}
