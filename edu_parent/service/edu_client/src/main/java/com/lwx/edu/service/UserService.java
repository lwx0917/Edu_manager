package com.lwx.edu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.User;
import com.lwx.edu.entity.vo.InfoVo;
import com.lwx.edu.entity.vo.UserVo;

/**
 * @author lwx
 * @since 2021-10-29
 */
public interface UserService extends IService<User> {

    void register(UserVo user);

    Integer loginByCode(String phone);

    Integer getId(String phone);

    Integer login(String phone, String password);

    void reviseInfo(InfoVo info);

    void reviseSalt(Integer id,String salt);

    void revisePhone(Integer id, String phone);

    void revisePassword(Integer id, String password);

    Integer confirmPhoneUnique(String newPhone);
}
