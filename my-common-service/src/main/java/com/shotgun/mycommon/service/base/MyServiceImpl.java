package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class MyServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIService<T> {


}
