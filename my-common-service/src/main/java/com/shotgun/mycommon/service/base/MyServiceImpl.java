package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shotgun.mycommon.base.util.JacksonUtils;

/**
 * 所有serviceImpl的基类，用于存放公共基础方法
 *
 **/
public class MyServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIService<T> {


    @Override
    public IPage<T> testGet10() {
        IPage<T> page = page(new Page<>(1, 10));
        System.out.println("testGet10:::" + JacksonUtils.writeValueAsString(page));
        return page;
    }
}
