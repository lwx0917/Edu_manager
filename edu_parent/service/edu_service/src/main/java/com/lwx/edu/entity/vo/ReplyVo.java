package com.lwx.edu.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyVo {
    private Integer id;
    private Integer userId;
    private Integer publishId;
    private String content;
    private String nickName;
    private String salt;
    private Integer good;
    private Integer bed;
    private Date gmtCreate;
//    private Integer mark;
//    @TableField(exist = false)
//    private List<ReplyVo> list;
}
