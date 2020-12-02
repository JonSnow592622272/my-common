package com.shotgun.mycommon.base.util.http.api;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface HttpClientInterface {
    String GET = "GET";
    String POST = "POST";

    /**
     * 头部信息
     */
    String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    String HEADER_KEY_ACCEPT_ENCODING = "Accept-Encoding";
    String HEADER_KEY_RANGE = "Range";

    /**
     * 头部信息默认值
     */
    String HEADER_VAL_CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    String HEADER_VAL_CONTENT_TYPE_JSON = "application/json";

    default String execute(String method, String url) {
        return execute(method, url, (Map<String, String>) null, null);
    }


    /**
     * 默认Content-Type:application/x-www-form-urlencoded
     * 如遇到“&#xxx;”字符编码，可使用StringEscapeUtils.unescapeHtml进行解码
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(非必填)
     * @param body    请求体(非必填)，get请求不会传递body
     * @return 返回结果
     **/
    default String execute(String method, String url, String headers, String body) {
        Map<String, String> headersMap;

        if (StringUtils.isEmpty(headers)) {
            headersMap = null;
        } else {
            headersMap = new HashMap<>();

            String[] ps = headers.split("\n");
            for (String p : ps) {
                String pTrim = p.trim();
                if (!StringUtils.isEmpty(pTrim)) {
                    int psi = pTrim.indexOf(":");
                    if (psi > 0) {
                        //只筛选出是请求头的头部信息
                        String headerValue = pTrim.substring(psi + 1);
                        if (headerValue.indexOf("//") == 0) {
                            //防止SoapUI、fiddler复制时带上请求地，如POST http://oa.ytport.com/sys/webservice HTTP/1.1
                            continue;
                        }
                        headersMap.put(pTrim.substring(0, psi).trim(), headerValue.trim());
                    }
                }
            }
        }

        return execute(method, url, headersMap, body);
    }


    /**
     * 所有请求统一入口
     * <p>
     * 默认Content-Type:application/x-www-form-urlencoded
     * 如遇到“&#xxx;”字符编码，可使用StringEscapeUtils.unescapeHtml进行解码
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(非必填)
     * @param body    请求体(非必填)，get请求不会传递body
     * @return 返回结果
     **/
    String execute(String method, String url, Map<String, String> headers, String body);

    default String httpGet(String url) {
        return execute(GET, url, (Map<String, String>) null, null);
    }

    default String httpPostForm(String url, Map<String, String> headers, String bodyForm) {
        if (headers != null && !HEADER_VAL_CONTENT_TYPE_FORM.equals(headers.get(HEADER_KEY_CONTENT_TYPE))) {
            //手动设置Content-Type，进行覆盖为application/x-www-form-urlencoded
            headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_FORM);
        }
        return execute(POST, url, headers, bodyForm);
    }

    default String httpPostJson(String url, Map<String, String> headers, String bodyJson) {
        if (headers == null) {
            //设置Content-Type为application/json
            headers = Collections.singletonMap(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_JSON);
        } else if (!HEADER_VAL_CONTENT_TYPE_JSON.equals(headers.get(HEADER_KEY_CONTENT_TYPE))) {
            //手动设置Content-Type，进行覆盖为application/json
            headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_JSON);
        }

        return execute(POST, url, headers, bodyJson);
    }


}
