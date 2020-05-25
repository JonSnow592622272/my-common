package com.shotgun.mycommon.base.base.api;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PageParam {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页错误")
    private Integer pageNum = 1;

    /**
     * 每页的数量
     */
    @NotNull(message = "每页的数量不能为空")
    @Min(value = 1, message = "每页的数量最小为{value}")
    @Max(value = 100, message = "每页的数量最大为{value}")
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
