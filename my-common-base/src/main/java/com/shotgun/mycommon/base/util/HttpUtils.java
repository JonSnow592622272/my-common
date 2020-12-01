package com.shotgun.mycommon.base.util;

import com.shotgun.mycommon.base.util.http.OkHttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    /**
     * LocalDateTime工具
     */
    public static final OkHttpClientUtil HTTP_CLIENT_OK = new OkHttpClientUtil();

    public static void main(String[] args) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/plain;charset=UTF-8");
        headers = null;

        System.out.println(HttpUtils.HTTP_CLIENT_OK.execute("get", "https://www.baidu.com", headers, null));
    }

}
