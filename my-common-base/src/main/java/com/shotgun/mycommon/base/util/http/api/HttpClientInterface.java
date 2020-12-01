package com.shotgun.mycommon.base.util.http.api;

import java.io.IOException;
import java.util.Map;

public interface HttpClientInterface {
    String GET = "GET";
    String CONTENT_TYPE_FLAG = "Content-Type";
    String CONTENT_TYPE_DEFAULT = "application/x-www-form-urlencoded";

    /**
     * 默认Content-Type:application/x-www-form-urlencoded
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(可为null)
     * @param body    请求体(可为null)
     * @return 返回结果
     **/
    default String execute(String method, String url, String headers, String body) throws IOException {
        return "";
    }

    /**
     * 默认Content-Type:application/x-www-form-urlencoded
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(可为null)
     * @param body    请求体(可为null)
     * @return 返回结果
     **/
    String execute(String method, String url, Map<String, String> headers, String body) throws IOException;

}
