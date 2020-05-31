package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shotgun.mycommon.base.base.api.BombApi;
import com.shotgun.mycommon.base.base.api.ResultInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author wulm
 * 所有serviceImpl的基类，用于存放公共基础方法
 * 公共方法命名规范：增删改查：insert,update,delete,get
 **/
public interface BombService<T> extends BombApi<T> /*extends IService<T>这里注释掉是为了不暴露给其他service使用，避免其他service
实现类调用里面的方法来绕过逻辑验证*/ {

    /**
     * 返回成功状态码
     *
     * @return 成功状态码
     **/
    ResultInfo success();

    IPage<T> baseTestGet10(String a, String b);


    @Transactional(rollbackFor = Exception.class)
    @Override
    default ResultInfo insertBatch(T... records) {
        return insertBatchUsePage(1000, records);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    default ResultInfo insertBatch(Collection<T> records) {
        return baseInsertBatchUsePage(1000, records);
    }

    /**
     * 分页批量插入
     *
     * @param batchSize 每批数量大小
     * @param records   数据
     * @return 结果
     **/
    @Transactional(rollbackFor = Exception.class)
    default ResultInfo insertBatchUsePage(int batchSize, T... records) {
        return baseInsertBatchUsePage(batchSize, Arrays.asList(records));
    }

    /**
     * 分页批量插入
     *
     * @param batchSize 每批数量大小
     * @param records   数据
     * @return 结果
     **/
    ResultInfo baseInsertBatchUsePage(int batchSize, Collection<T> records);

}
