package com.shotgun.mycommon.base.base.valid;

/**
 * 验证器分组
 *
 * @author wulm
 **/
public interface Goups {
    /**
     * 全字段新增
     **/
    interface Insert {}

    /*    interface Delete {} 删除就避免封装到实体类去验证，直接方法参数上去加验证，比如：test(@RequestParam @Length(max = 4, message
    ="长度不能大于{max}") String a, @RequestParam String b)*/

    /**
     * 根据id进行全字段修改
     **/
    interface UpdateById {}

    /**
     * 可选字段修改，比如:根据手机号修改姓名，只用到手机号、姓名0
     **/
    interface UpdateSelective {}

    /**
     * 保留
     **/
    interface Select {}

}
