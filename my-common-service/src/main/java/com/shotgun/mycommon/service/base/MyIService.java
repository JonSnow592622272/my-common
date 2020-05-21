package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shotgun.mycommon.base.base.Code;
import com.shotgun.mycommon.base.base.ResultInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author wulm
 * 这里不要使用接口默认实现，否则无法在serviceApi中使用同名接口
 **/
public interface MyIService<T> /*extends IService<T> 这里注释掉是为了不暴露给其他service使用，避免其他service实现类调用里面的方法来绕过逻辑验证*/ {
    Code SUCCESS_CODE = new Code() {
        @Override
        public String getSystemCode() {
            return "common";
        }

        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getText() {
            return "";
        }
    };

    IPage<T> testGet10(String a, String b);

    /**
     * 单条插入
     **/
    default ResultInfo insert(T record){
        return insertBatch(Collections.singletonList(record));
    }

    /**
     * 批量插入
     **/
    @Transactional(rollbackFor = Exception.class)
    default ResultInfo insertBatch(T... records){
        return insertBatch(Arrays.asList(records));
    }

    /**
     * 批量插入
     **/
    @Transactional(rollbackFor = Exception.class)
    default ResultInfo insertBatch(Collection<T> records){
        return insertBatchUsePage(1000, records);
    }

    /**
     * 分页批量插入
     **/
    @Transactional(rollbackFor = Exception.class)
    default ResultInfo insertBatchUsePage(int batchSize, T... records){
        return insertBatchUsePage(batchSize, Arrays.asList(records));
    }

    /**
     * 分页批量插入
     **/
    ResultInfo insertBatchUsePage(int batchSize, Collection<T> records);

}
