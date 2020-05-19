package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shotgun.mycommon.base.base.ResultInfo;
import com.shotgun.mycommon.base.util.JacksonUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author wulm
 * 所有serviceImpl的基类，用于存放公共基础方法
 **/
public class MyServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIService<T> {



    //消息一部：对象json转给消息中间件，消息消费调用原提供接口方（缺点：跨数据源多步操作无事务控制）

    @Override
    public IPage<T> testGet10() {

        IPage<T> page = page(new Page<>(1, 10));
        System.out.println("testGet10:::" + JacksonUtils.writeValueAsString(page));
        return page;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultInfo insertBatchUsePage(int batchSize, Collection<T> records) {
        saveBatch(records, batchSize);
        return new ResultInfo<>(SUCCESS_CODE);
    }

}
