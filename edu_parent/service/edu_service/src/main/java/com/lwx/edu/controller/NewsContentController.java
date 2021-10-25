package com.lwx.edu.controller;


import com.lwx.edu.entity.NewsContent;
import com.lwx.edu.service.NewsContentService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lwx
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/edu/newsContent")
@CrossOrigin
public class NewsContentController {

    @Resource
    private NewsContentService newsContentService;

    @PostMapping("/addContent")
    public Result addContent(@RequestBody NewsContent newsContent) {
        Integer id = newsContentService.addContent(newsContent);
        return Result.ok().data("result",id);
    }

}

