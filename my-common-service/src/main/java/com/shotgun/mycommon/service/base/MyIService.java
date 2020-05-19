package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MyIService<T> extends IService<T> {

    IPage<T> testGet10();


}
