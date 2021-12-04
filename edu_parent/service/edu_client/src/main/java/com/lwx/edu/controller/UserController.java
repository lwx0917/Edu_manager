package com.lwx.edu.controller;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.lwx.edu.config.JwtConfig;
import com.lwx.edu.entity.User;
import com.lwx.edu.entity.vo.InfoVo;
import com.lwx.edu.entity.vo.UserVo;
import com.lwx.edu.service.StatisticsDailyService;
import com.lwx.edu.service.UserService;
import com.lwx.edu.utils.AliUtils;
import com.lwx.edu.utils.RandomUtils;
import com.lwx.edu.utils.RedisUtils;
import com.lwx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lwx
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/server/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AliUtils aliUtils;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private StatisticsDailyService dailyService;

    @GetMapping("/getUserInfo/{id}")
    public Result getUserInfo(@PathVariable String id) {
        try {
            User user = userService.getById(id);
            return Result.ok().data("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }

    @GetMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone) {
        try {
            String code = RandomUtils.verificationCode();
            aliUtils.send(phone, code);
            redisUtils.set(phone, code, 300);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("发送失败");
        }
    }

    @PostMapping("/register/{verityCode}/{phone}")
    public Result register(@PathVariable String phone, @PathVariable String verityCode) {
        try {
            log.error(phone);
            log.error(redisUtils.get(phone).toString());
            if (redisUtils.get(phone) == null) {
                return Result.error().message("手机号不存在");
            } else {
                log.error(redisUtils.get(phone).toString());
                if (redisUtils.get(phone).toString().equals(verityCode)) {
                    UserVo vo = new UserVo();
                    vo.setPhone(phone);
                    vo.setPassword(SecureUtil.md5("123"));
                    vo.setNickName(RandomUtils.randomName());
                    vo.setSalt("https://my-edu-demo.oss-cn-shenzhen.aliyuncs.com/static/default.jpg");
                    userService.register(vo);
                    dailyService.updateRegister();
                    return Result.ok();
                } else {
                    return Result.error().message("验证码错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("出现错误");
        }
    }

    @PostMapping("/loginByCodes/{verityCode}/{phone}")
    public Result loginByCode(@PathVariable String phone, @PathVariable String verityCode) {
        try {
            if (redisUtils.get(phone) != null) {
//                log.error(redisUtils.get(phone).toString());
                if (redisUtils.get(phone).toString().equals(verityCode)) {
                    Integer result = userService.loginByCode(phone);
                    if (result > 0) {
                        Integer id = userService.getId(phone);
                        String token = jwtConfig.createToken(phone);
                        dailyService.updateLogin();
                        return Result.ok().data("id", id).data("token", token);
                    } else {
                        return Result.error().message("用户不存在");
                    }
                } else {
                    return Result.error().message("验证码错误");
                }
            } else {
                return Result.error().message("手机号不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("出现错误");
        }
    }

    @PostMapping("/login/{phone}/{password}")
    public Result login(@PathVariable String phone, @PathVariable String password) {
        try {
            Integer result = userService.login(phone, password);
            if (result > 0) {
                Integer id = userService.getId(phone);
                String token = jwtConfig.createToken(phone);
                dailyService.updateLogin();
                return Result.ok().data("id", id).data("token", token);
            }
            return Result.error();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("出现错误");
        }
    }

    @PostMapping("/reviseInfo")
    public Result reviseInfo(@RequestBody InfoVo info) {
        try {
            userService.reviseInfo(info);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("修改失败");
        }
    }

    @PostMapping("/reviseSalt/{id}")
    public Result reviseSalt(@PathVariable Integer id, @RequestBody Map<String, String> map) {
        try {
//            log.error(map.toString());
            userService.reviseSalt(id, map.get("salt"));
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("修改失败");
        }
    }

    @GetMapping("/confirmPhoneUnique/{newPhone}")
    public Result confirmPhoneUnique(@PathVariable String newPhone) {
        try {
            Integer result = userService.confirmPhoneUnique(newPhone);
            if (result == 0) {
                return Result.ok().message("手机号可用");
            } else {
                return Result.error().message("手机号已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/revisePhone/{id}/{newPhone}/{verityCode}")
    public Result revisePhone(@PathVariable Integer id, @PathVariable String newPhone, @PathVariable String verityCode) {
        try {
            if (redisUtils.get(newPhone) != null) {
                if (redisUtils.get(newPhone).toString().equals(verityCode)) {
                    userService.revisePhone(id, newPhone);
                } else {
                    return Result.error().message("验证码错误");
                }
            } else {
                return Result.error().message("手机号不存在");
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("修改失败");
        }
    }

    @PostMapping("/revisePassword/{id}/{password}")
    public Result revisePassword(@PathVariable Integer id, @PathVariable String password) {
        try {
            userService.revisePassword(id, password);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("修改失败");
        }
    }
}

