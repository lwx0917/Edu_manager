package com.lwx.edu.config;

import com.lwx.edu.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/server/user/sendCode/**");
        list.add("/server/user/register/**");
        list.add("/server/user/loginByCodes/**");
        list.add("/server/user/login/**");
        list.add("/server/course/**");
        list.add("/server/comment/getComments/**");
        list.add("/server/comment/getCounts/**");
        list.add("/server/teacher/**");
        list.add("/server/discuss/getDiscuss");
        list.add("/server/discuss/getDiscussById/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")
                .excludePathPatterns(list)
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}