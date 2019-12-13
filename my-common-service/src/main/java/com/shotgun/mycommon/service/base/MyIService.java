package com.shotgun.mycommon.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface MyIService<U> extends IService<U> {

    default List<U> testHaha() {
        return list();
    }


}
