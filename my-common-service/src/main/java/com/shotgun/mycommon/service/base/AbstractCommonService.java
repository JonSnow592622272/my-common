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
 * 所有serviceImpl的基类，用于存放公共基础方法。不能在XXXServiceApi中创建与当该类方法同名接口，否则会引起feign的验证问题
 **/
public abstract class AbstractCommonService<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T> implements CommonService<T> {

    /**
     * 返回成功状态码
     * @return 成功状态码
     **/
    protected abstract ResultInfo success();

    //消息一部：对象json转给消息中间件，消息消费调用原提供接口方

    @Override
    public IPage<T> baseTestGet10(String a, String b) {

        //查询分页数据
        IPage<T> page = page(new Page<>(1, 10));
        System.out.println("testGet10:::" + JacksonUtils.writeValueAsString(page));
        return page;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultInfo baseInsertBatchUsePage(int batchSize, Collection<T> records) {
        saveBatch(records, batchSize);
        return success();
    }

}