package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MyBaseMapper<U> extends BaseMapper<U> {

    default List<U> test() {
        return selectList(new LambdaQueryWrapper<>());
    }


}
