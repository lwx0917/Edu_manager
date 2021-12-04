package com.lwx.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.edu.entity.User;
import com.lwx.edu.entity.vo.InfoVo;
import com.lwx.edu.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lwx
 * @since 2021-10-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void register(UserVo vo);

    Integer loginByCode(String phone);

    Integer getId(String phone);

    Integer login(String phone, String password);

    void reviseInfo(UpdateWrapper<InfoVo> wrapper);

    void revisePassword(@Param(Constants.WRAPPER)QueryWrapper<User> wrapper,String password);
}
