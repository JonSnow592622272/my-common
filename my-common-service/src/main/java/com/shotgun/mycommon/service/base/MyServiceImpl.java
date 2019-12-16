package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shotgun.mycommon.base.util.JacksonUtils;
import org.springframework.web.bind.annotation.GetMapping;

public class MyServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIService<T> {


    @GetMapping("/testGet10")
    @Override
    public IPage<T> testGet10() {
        IPage<T> page = page(new Page<>(1, 10));
        System.out.println("testGet10:::" + JacksonUtils.writeValueAsString(page));
        return page;
    }
}
