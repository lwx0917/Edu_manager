package com.lwx.edu.entity.vo;
import lombok.Data;

import java.util.Date;

@Data
public class DiscussVo {
    private Integer id;
    private Integer userId;
    private String content;
    private String nickName;
    private String sign;
    private String salt;
    private Integer good;
    private Integer bed;
    private Integer num;
    private Date gmtCreate;

}
