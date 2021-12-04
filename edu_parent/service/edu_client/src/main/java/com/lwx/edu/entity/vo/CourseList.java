package com.lwx.edu.entity.vo;

import com.lwx.edu.entity.vo.ChapterList;
import lombok.Data;

import java.util.List;


@Data
public class CourseList {

    private List<ChapterList> chapters;
}
