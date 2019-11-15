package com.shotgun.mycommon.base.base;

import java.io.Serializable;

public class ResultInfo<E> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 系统编码 */
	private String systemCode;

	/** 状态码 */
	private Integer statusCode;

	/** 返回提示信息 */
	private String msg;

	/** 返回数据 */
	private E data;

	private ResultInfo() {
	}

    public ResultInfo(String systemCode, Code code) {
        this.systemCode = systemCode;
        this.statusCode = code.getValue();
        this.msg = code.getText();
    }

    public ResultInfo(String systemCode, Code code, E data) {
        this.systemCode = systemCode;
        this.statusCode = code.getValue();
        this.msg = code.getText();
        this.data = data;
    }

    /*是否失败*/
    public boolean isFailed() {
        return this.statusCode > 0;
    }

    /*是否成功*/
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
