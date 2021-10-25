package com.lwx.edu.controller;


import com.lwx.edu.entity.NewsInfo;
import com.lwx.edu.entity.vo.NewsVo;
import com.lwx.edu.service.NewsInfoService;
import com.lwx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/edu/newsInfo")
@CrossOrigin
@Slf4j
public class NewsInfoController {

    @Resource
    private NewsInfoService newsInfoService;

    @PostMapping("/addInfo")
    public Result addInfo(@RequestBody NewsInfo newsInfo) {
        try {
            Integer id = newsInfoService.addInfo(newsInfo);
            return Result.ok().data("result", id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("出现错误");
        }
    }

    @GetMapping("/getNewsInfo/{id}")
    public Result getNewsInfo(@PathVariable String id) {
        try {
            NewsVo news = newsInfoService.getNewsInfo(id);
            return Result.ok().data("news", news);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("请求错误");
        }
    }

    @GetMapping("/publishNews/{id}")
    public Result publishNews(@PathVariable String id) {
        try {
            newsInfoService.publishNews(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("发布失败");
        }
    }

    @GetMapping("/getAllNews")
    public Result getAllNews() {
        List<NewsVo> newsVoList = newsInfoService.getAllNews();
        return Result.ok().data("result", newsVoList);
    }
}

