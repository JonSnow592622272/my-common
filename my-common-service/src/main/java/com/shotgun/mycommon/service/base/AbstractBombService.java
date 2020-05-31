package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shotgun.mycommon.base.base.api.ResultInfo;
import com.shotgun.mycommon.base.util.JacksonUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author wulm
 **/
public abstract class AbstractBombService<M extends BombMapper<T>, T> extends ServiceImpl<M, T> implements BombService<T> {

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
