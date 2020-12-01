package com.shotgun.mycommon.base.util.http;

import com.shotgun.mycommon.base.util.http.api.HttpClientInterface;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class OkHttpClientUtil implements HttpClientInterface {

    private Logger logger = LoggerFactory.getLogger(OkHttpClientUtil.class);


    @Override
    public String execute(String method, String url, Map<String, String> headers,
            String body) throws IOException {

        RequestBody requestBody;

        method = method.toUpperCase();

        if (GET.equals(method)) {
            //get请求没有requestBody
            requestBody = null;
            if (headers == null) {
                headers = Collections.emptyMap();
            }
        } else {
            if (headers == null) {
                //设置默认Content-Type
                headers = Collections.singletonMap(CONTENT_TYPE_FLAG, CONTENT_TYPE_DEFAULT);
            } else if (!headers.containsKey(CONTENT_TYPE_FLAG)) {
                //设置默认Content-Type
                headers.put(CONTENT_TYPE_FLAG, CONTENT_TYPE_DEFAULT);
            }
            requestBody = StringUtils.isEmpty(body) ? Util.EMPTY_REQUEST : RequestBody
                    .create(MediaType.get(headers.get(CONTENT_TYPE_FLAG)), body);
        }


        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();


        // 构造Request
        Request request = new Request.Builder().url(url).method(method, requestBody)
                .headers(headers.isEmpty() ? new Headers.Builder().build() : Headers.of(headers)).build();

        try {
            Call call = mOkHttpClient.newCall(request);

            //发起请求
            Response response = call.execute();

            ResponseBody responseBody = response.body();
            return responseBody == null ? "" : responseBody.string();
//            if (response.isSuccessful()) {
//            }
        } catch (Exception e) {
            logger.error("http请求异常！！！", e);
            return "";
        }
    }

}
