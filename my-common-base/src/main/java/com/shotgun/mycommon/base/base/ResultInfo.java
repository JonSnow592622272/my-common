package com.shotgun.mycommon.base.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultInfo<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码分界线，大于该值的所有状态都表示错误
     */
    private static final int STATUS_CODE_DIVIDING = 1;

    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 状态码
     */
    private Integer statusCode;

    /**
     * 返回提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private E data;

    private ResultInfo() {
    }

    public ResultInfo(Code... errorCodes) {
        this.systemCode = errorCodes[0].getSystemCode();
        this.statusCode = STATUS_CODE_DIVIDING;
        this.msg = "操作成功";

        Map<String, Object> data = new HashMap<>();
        data.put("systemCodes", Stream.of(errorCodes).map(Code::getSystemCode).collect(Collectors.toList()));
        data.put("statusCodes", Stream.of(errorCodes).map(Code::getValue).collect(Collectors.toList()));
        data.put("msgs", Stream.of(errorCodes).map(Code::getText).collect(Collectors.toList()));

        this.data = (E) data;
    }

    public ResultInfo(Code code) {
        this.systemCode = code.getSystemCode();
        this.statusCode = code.getValue();
        this.msg = code.getText();
    }

    public ResultInfo(Code code, E data) {
        this.systemCode = code.getSystemCode();
        this.statusCode = code.getValue();
        this.msg = code.getText();
        this.data = data;
    }

    /*是否失败*/
    @JsonIgnore
    public boolean isFailed() {
        return this.statusCode > STATUS_CODE_DIVIDING;
    }

    /*是否成功*/
    @JsonIgnore
    public boolean isSuccess() {
        return !isFailed();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
