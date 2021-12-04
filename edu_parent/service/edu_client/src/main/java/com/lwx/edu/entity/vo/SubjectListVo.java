package com.lwx.edu.entity.vo;

import com.lwx.edu.entity.vo.ChildSubjectVo;
import lombok.Data;

import java.util.List;

@Data
public class SubjectListVo {
    private String id;
    private String title;
    private List<ChildSubjectVo> children;
}
