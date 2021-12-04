package com.lwx.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lwx.edu.entity.Share;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.service.ShareService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwx
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/edu/share")
@CrossOrigin
public class ShareController {

    @Resource
    private ShareService shareService;

    @PostMapping("/getSharePage")
        public Result getSharePage(@RequestBody PageQuery pageQuery) {
        try {
            IPage<Share> page = shareService.getSharePage(pageQuery);
            long total = page.getTotal();
            List<Share> records = page.getRecords();
            return Result.ok().data("total", total).data("data", records);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/addShare")
    public Result addShare(@RequestBody Share share) {
        try {
            shareService.save(share);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/getShareById/{id}")
    public Result getShareById(@PathVariable String id) {
        try {
            Share share = shareService.getShareById(id);
            return Result.ok().data("data",share);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/updateShare/{id}")
    public Result updateShare(@PathVariable String id,@RequestBody Share share) {
        try {
            shareService.updateShare(id,share);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @DeleteMapping("/delShare/{id}")
    public Result delShare(@PathVariable String id) {
        try {
            boolean result = shareService.removeById(id);
            return result ? Result.ok() : Result.error();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}

