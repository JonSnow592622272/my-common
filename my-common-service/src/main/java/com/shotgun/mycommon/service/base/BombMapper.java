package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author wulm
 **/
public interface BombMapper<T> extends BaseMapper<T> {

    default List<T> test() {
        return selectList(new LambdaQueryWrapper<>());
    }


}
