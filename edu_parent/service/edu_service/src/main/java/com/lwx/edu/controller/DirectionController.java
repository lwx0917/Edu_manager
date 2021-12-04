package com.lwx.edu.controller;


import com.lwx.edu.entity.Direction;
import com.lwx.edu.service.DirectionService;
import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-10-25
 */
@RestController
@RequestMapping("/edu/direction")
@CrossOrigin
public class DirectionController {

    @Resource
    private DirectionService directionService;

    @GetMapping("/getParentDirection")
    public Result getParentDirection() {
        List<Map<String, Object>> directions = directionService.getParentDirection();
        return Result.ok().data("directions", directions);
    }

    @GetMapping("/getHotDirection")
    public Result getHotDirection() {
        try {
            List<Map<String, Object>> direction = directionService.getHotDirection();
            return Result.ok().data("direction", direction);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/getDirection")
    public Result getDirection() {
        try {
            List<Map<String, Object>> direction = directionService.getDirection();
            return Result.ok().data("direction", direction);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @PostMapping("/addDirection")
    public Result addDirection(@RequestBody Direction direction) {
        try {
            directionService.addDirection(direction);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("添加失败");
        }
    }
}

