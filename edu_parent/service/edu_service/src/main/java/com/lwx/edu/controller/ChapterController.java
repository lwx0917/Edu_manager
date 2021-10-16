package com.lwx.edu.controller;


import com.lwx.edu.entity.Chapter;
import com.lwx.edu.entity.vo.ChapterVo;
import com.lwx.edu.service.ChapterService;
import com.lwx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author lwx
 * @since 2021-10-13
 */
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/getChapters/{id}")
    public Result getChapters(@PathVariable String id) {
        List<ChapterVo> chapters = chapterService.nestedList(id);
        return Result.ok().data("chapters", chapters);
    }

    @GetMapping("/getChapter/{id}")
    public Result getChapter(@PathVariable String id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok().data("chapter", chapter);
    }

    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok();
    }

    @PostMapping("/updateChapter/{id}")
    public Result updateChapter(@PathVariable String id, @RequestBody Chapter chapter) {
        chapter.setId(id);
        chapterService.updateById(chapter);
        return Result.ok();
    }

    @DeleteMapping("/delChapter/{id}")
    public Result delChapter(@PathVariable String id) throws Exception {
        chapterService.removeChapterById(id);
        return Result.ok();
    }
}

