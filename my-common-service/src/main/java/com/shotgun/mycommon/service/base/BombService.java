package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shotgun.mycommon.base.base.api.BombApi;
import com.shotgun.mycommon.base.base.api.ResultInfo;
import com.shotgun.mycommon.base.util.JacksonUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author wulm
 * 所有serviceImpl的基类，用于存放公共基础方法
 * 公共方法命名规范：增删改查：insert,update,delete,get （开头使用base作为前缀标识为公共方法）
 **/
public interface BombService<T> extends BombApi<T> /*extends IService<T> 这里注释掉是为了不暴露给其他service使用，避免其他service实现类调用里面的方法来绕过逻辑验证*/ {

    /**
     * 返回成功状态码
     *
     * @return 成功状态码
     **/
    ResultInfo success();

    IPage<T> baseTestGet10(String a, String b);

    /**
     * 单条插入
     **/
    default ResultInfo baseInsert(T record) {
        return baseInsertBatch(Collections.singletonList(record));
    }

    /**
     * 批量插入
     **/
    @Transactional(rollbackFor = Exception.class)
    default ResultInfo baseInsertBatch(T... records) {
        return baseInsertBatch(Arrays.asList(records));
    }

    /**
     * 批量插入
     **/
    @Transactional(rollbackFor = Exception.class)
    default ResultInfo baseInsertBatch(Collection<T> records) {
        return baseInsertBatchUsePage(1000, records);
    }

    /**
     * 分页批量插入
     **/
    @Transactional(rollbackFor = Exception.class)
    default ResultInfo baseInsertBatchUsePage(int batchSize, T... records) {
        return baseInsertBatchUsePage(batchSize, Arrays.asList(records));
    }

    /**
     * 分页批量插入
     **/
    ResultInfo baseInsertBatchUsePage(int batchSize, Collection<T> records);

    @Override
    default ResultInfo baseInsert66666(T record) {
        System.out.println("record::::"+JacksonUtils.writeValueAsString(record));
        return success();
    }

    @Transactional
    @Override
    default ResultInfo insertBatch77777(Collection<T> records) {
        System.out.println("ssssssssssssssssssssssssssssssssOK"+JacksonUtils.writeValueAsString(records));
        return success();
    }

}
