package com.shotgun.mycommon.base.base.api;

import com.shotgun.mycommon.base.base.valid.Goups;
import com.shotgun.mycommon.base.base.valid.ValidCollection;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Validated
public interface BombApi<T> {

    @PostMapping("/insertBatch2")
    @Validated(Goups.Insert.class)
    ResultInfo insertBatch2(@RequestBody @Valid ValidCollection<T> records);


}