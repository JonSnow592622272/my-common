package com.shotgun.mycommon.base.base.api;

import com.shotgun.mycommon.base.base.valid.Goups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;

@Validated
public interface BombApi<T> {

    /**
     * 单条插入
     *
     * @param record 数据
     * @return 结果
     **/
    ResultInfo insert(T record);

    /**
     * 批量插入
     *
     * @param records 数据
     * @return 结果
     **/
    ResultInfo insertBatch(T... records);

    /**
     * 批量插入
     *
     * @param records 数据
     * @return 结果
     **/
    ResultInfo insertBatch(Collection<T> records);

    /**
     * 分页批量插入
     *
     * @param batchSize 每批数量大小
     * @param records   数据
     * @return 结果
     **/
    ResultInfo insertBatchUsePage(int batchSize, T... records);

    /**
     * 分页批量插入
     *
     * @param batchSize 每批数量大小
     * @param records   数据
     * @return 结果
     **/
    ResultInfo baseInsertBatchUsePage(int batchSize, Collection<T> records);



    ////////////////////////


    @PostMapping("/insertBatch2")
    @Validated(Goups.Insert.class)
    ResultInfo insertBatch2(@RequestBody @Valid Collection<T> records);

    @PostMapping("/insertBatch77777")
    @Validated(Goups.Update.class)
    ResultInfo insertBatch77777(@RequestBody @Valid Collection<T> records);


    @PostMapping("/baseInsert66666")
    @Validated(Goups.Update.class)
    ResultInfo baseInsert66666(@RequestBody @Valid T record);

}