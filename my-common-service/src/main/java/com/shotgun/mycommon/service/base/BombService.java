package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shotgun.mycommon.base.base.api.BombApi;
import com.shotgun.mycommon.base.base.api.ResultInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * @author wulm
 * 所有serviceImpl的基类，用于存放公共基础方法
 * 公共方法命名规范：增删改查：insert,update,delete,get
 **/
public interface BombService<T> extends BombApi<T> /*extends IService<T>这里注释掉是为了不暴露给其他service使用，避免其他service
实现类调用里面的方法来绕过逻辑验证*/ {

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓Api默认实现↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


    @Override
    default ResultInfo insert(T record) {
        //单条数据不使用事务
        return insertBatch(Collections.singletonList(record));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    default ResultInfo insertBatch(Collection<T> records) {
        return baseInsertBatchUsePage(1000, records);
    }

    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑Api默认实现↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 返回成功状态码
     *
     * @return 成功状态码
     **/
    ResultInfo success();

    IPage<T> baseTestGet10(String a, String b);



    /**
     * 批量插入分页，所有插入统一最终入口
     *
     * @param batchSize 每批数量大小
     * @param records   数据
     * @return 结果
     **/
    ResultInfo baseInsertBatchUsePage(int batchSize, Collection<T> records);

}
