package com.lwx.edu;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.lwx.edu.entity.Comment;
import com.lwx.edu.entity.query.PageQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Connection {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testConnect() {
//        redisTemplate.opsForValue().set("myKey", "myValue");
//        System.out.println(redisTemplate.opsForValue().get("myKey"));
        Map<String, Object> map = new HashMap<>();
        map.put("one",1);
        map.put("two",2);
        System.out.println(map.toString());
    }

    @Test
    public void testMap(){
        String str = "{\"current\":1,\"size\":3}";
        Gson gson = new Gson();
        PageQuery pageQuery = new PageQuery();
//        Map map = new HashMap<>();
        pageQuery = gson.fromJson(str, pageQuery.getClass());
//        String strLong = (String) map.get("content");
//        System.out.println(strLong);
        System.out.println(pageQuery.toString());
    }

    @Test
    public void testV(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("One",1);
        map.put("Two",2);
        System.out.println(map);
    }

    @Test
    public void trans(){
        String json = "{\"avatar\":\"https://my-edu-demo.edu-cn-shenzhen.aliyuncs.com/2021/11/19/47ce6b70-44fa-4eed-beec-e426d17dd6a03F7CFE66B730DDAA4DC4D8170AAE46BE.jpg\",\"content\":\"111\",\"courseId\":\"1454738465567522817\",\"nickName\":\"丨\",\"teacherId\":\"\",\"userId\":\"1\"}";
        Comment comment = JSONUtil.toBean(json, Comment.class);
        System.out.println(comment.toString());
    }

    @Test
    public void testMd(){
        String s = SecureUtil.md5(String.valueOf(123));
        System.out.println(s);
    }

    @Test
    public void testTime(){
        Date nowDate = new Date();
        Date expire = new Date(nowDate.getTime());
        Date expireDate = new Date(nowDate.getTime() + 360000);//过期
        System.out.println(expire);
        System.out.println(expireDate);
    }

}
