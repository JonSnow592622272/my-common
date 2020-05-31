package com.shotgun.mycommon.base.base.api;

import com.shotgun.mycommon.base.base.valid.Goups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

/**
 * 基础接口的url以base开头，后面用简写，并非方法名，比如:@PostMapping("/basei")
 * @author wulm
 **/
@Validated
public interface BombApi<T> {

    /**
     * 单条插入
     *
     * @param record 数据
     * @return 结果
     **/
    @PostMapping("/basei")
    @Validated(Goups.Insert.class)
    default ResultInfo insert(@RequestBody @Valid T record) {
        return insertBatch(Collections.singletonList(record));
    }

    /**
     * 批量插入
     *
     * @param records 数据
     * @return 结果
     **/
    @PostMapping("/baseib")
    @Validated(Goups.Insert.class)
    ResultInfo insertBatch(@RequestBody @Valid T... records);

    /**
     * 批量插入
     *
     * @param records 数据
     * @return 结果
     **/
    @PostMapping("/baseib2")
    @Validated(Goups.Insert.class)
    ResultInfo insertBatch(@RequestBody @Valid Collection<T> records);




    ////////////////////////↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑所有api基础方法，已定好.....


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