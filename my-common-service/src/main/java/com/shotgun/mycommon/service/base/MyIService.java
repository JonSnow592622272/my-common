package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MyIService<T> extends IService<T> {

    Page<T> testGet10();


}
