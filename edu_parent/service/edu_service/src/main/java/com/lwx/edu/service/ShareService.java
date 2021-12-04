package com.lwx.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lwx.edu.entity.Share;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.query.PageQuery;

/**
 * @author lwx
 * @since 2021-11-25
 */
public interface ShareService extends IService<Share> {

    IPage<Share> getSharePage(PageQuery pageQuery);

    Share getShareById(String id);

    void updateShare(String id, Share share);
}
