package com.lwx.edu.utils;

import com.google.gson.Gson;
import com.lwx.edu.entity.query.PageQuery;


public class BaseUtils {
    public static PageQuery getValue(String param) {
        Gson gson = new Gson();
        PageQuery pageQuery = new PageQuery();
        pageQuery = gson.fromJson(param, pageQuery.getClass());
        System.out.println(pageQuery.toString());
        return pageQuery;
    }

}
