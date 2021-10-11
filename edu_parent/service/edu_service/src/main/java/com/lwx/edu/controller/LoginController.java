package com.lwx.edu.controller;

import com.lwx.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edu/user")
@CrossOrigin
public class LoginController {


    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token","admin");
    }

    @GetMapping("/info")
    public Result getInfo() {
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/07/91871e25-fd83-4af6-845f-ea8d471d825d.png");
    }
}
