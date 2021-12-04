package com.lwx.edu.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lwx.edu.entity.User;
import com.lwx.edu.entity.vo.InfoVo;
import com.lwx.edu.entity.vo.UserVo;
import com.lwx.edu.mapper.UserMapper;
import com.lwx.edu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwx
 * @since 2021-10-29
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserVo user) {
        userMapper.register(user);
    }

    @Override
    public Integer loginByCode(String phone) {
        return userMapper.loginByCode(phone);
    }

    @Override
    public Integer getId(String phone) {
        return userMapper.getId(phone);
    }

    @Override
    public Integer login(String phone, String password) {
        password = SecureUtil.md5(password);
        return userMapper.login(phone, password);
    }

    @Override
    public void reviseInfo(InfoVo info) {
        UpdateWrapper<InfoVo> wrapper = new UpdateWrapper<>();
        String nickName = info.getNickName();
        if (!StringUtils.isEmpty(nickName)) {
            wrapper.set("nickName", nickName);
        }
        userMapper.reviseInfo(wrapper);
    }

    @Override
    public void reviseSalt(Integer id, String salt) {
        User user = new User();
        user.setId(id);
        user.setSalt(salt);
        baseMapper.updateById(user);
    }

    @Override
    public void revisePhone(Integer id, String phone) {
        User user = new User();
        user.setId(id);
        user.setPhone(phone);
        baseMapper.updateById(user);
    }

    @Override
    public void revisePassword(Integer id, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        password = SecureUtil.md5(password);
        baseMapper.revisePassword(wrapper,password);
    }

    @Override
    public Integer confirmPhoneUnique(String newPhone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",newPhone);
        return baseMapper.selectCount(wrapper);
    }
}
