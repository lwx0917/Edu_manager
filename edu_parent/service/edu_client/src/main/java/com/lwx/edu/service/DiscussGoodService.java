package com.lwx.edu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.DiscussGood;

/**
 * @author lwx
 * @since 2021-11-24
 */
public interface DiscussGoodService extends IService<DiscussGood> {

    Integer confirm(String publishId, String userId);

    void add(String id, String userId);
}
