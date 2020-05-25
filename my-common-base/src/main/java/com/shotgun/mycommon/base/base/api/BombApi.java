package com.shotgun.mycommon.base.base.api;

import com.shotgun.mycommon.base.base.valid.Goups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;

@Validated
public interface BombApi<T> {

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