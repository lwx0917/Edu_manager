package com.lwx.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQuery implements Serializable {

    private static final long serializableUID = 1L;
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "最小课时")
    private String minLessonNum;
    @ApiModelProperty(value = "最大课时")
    private String maxLessonNum;
}

