package com.shotgun.mycommon.base.base.api;

import com.shotgun.mycommon.base.base.valid.Goups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;

/**
 * 基础接口的url以base开头，后面用简写，并非方法名，比如:@PostMapping("/basei")
 *
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
    ResultInfo insert(@RequestBody @Valid T record);

    /**
     * 批量插入
     *
     * @param records 数据
     * @return 结果
     **/
    @PostMapping("/baseibCol")
    @Validated(Goups.Insert.class)
    ResultInfo insertBatch(@RequestBody @Valid Collection<T> records);

    /**
     * 批量插入
     *
     * @param records 数据
     * @return 结果
     **/
    @PostMapping("/baseibArr")
    @Validated(Goups.Insert.class)
    default ResultInfo insertBatch(@RequestBody @Valid T... records) {
        return insertBatch(Arrays.asList(records));
    }




}