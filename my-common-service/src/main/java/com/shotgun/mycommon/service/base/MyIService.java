package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shotgun.mycommon.base.base.ResultInfo;

import java.util.Collection;

/**
 * @author wulm
 **/
public interface MyIService<T> /*extends IService<T> 这里注释掉是为了不暴露给其他service使用，避免其他service实现类调用里面的方法来绕过逻辑验证*/ {

    IPage<T> testGet10();

    /**
     * 单条插入
     **/
    ResultInfo insert(T record);

    /**
     * 批量插入
     **/
    ResultInfo insertBatch(T... records);

    /**
     * 批量插入
     **/
    ResultInfo insertBatch(Collection<T> records);

    /**
     * 分页批量插入
     **/
    ResultInfo insertBatchUsePage(int batchSize, T... records);

    /**
     * 分页批量插入
     **/
    ResultInfo insertBatchUsePage(int batchSize, Collection<T> records);

}
