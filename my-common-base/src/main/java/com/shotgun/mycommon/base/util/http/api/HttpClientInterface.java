package com.shotgun.mycommon.base.util.http.api;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public interface HttpClientInterface {
    String GET = "GET";

    /**
     * 头部信息
     */
    String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    String HEADER_KEY_ACCEPT_ENCODING = "Accept-Encoding";
    String HEADER_KEY_RANGE = "Range";

    /**
     * 头部信息默认值
     */
    String HEADER_VAL_CONTENT_TYPE = "application/x-www-form-urlencoded";

    /**
     * 默认Content-Type:application/x-www-form-urlencoded
     * 如遇到“&#xxx;”字符编码，可使用StringEscapeUtils.unescapeHtml进行解码
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(可为null)
     * @param body    请求体(可为null)
     * @return 返回结果
     **/
    default String execute(String method, String url, String headers, String body) {
        Assert.notNull(method, "method不能为空!");
        Assert.notNull(url, "url不能为空!");

        Map<String, String> headersMap = new HashMap<>();

        if (!StringUtils.isEmpty(headers)) {
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
     * 默认Content-Type:application/x-www-form-urlencoded
     * 如遇到“&#xxx;”字符编码，可使用StringEscapeUtils.unescapeHtml进行解码
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(可为null)
     * @param body    请求体(可为null)
     * @return 返回结果
     **/
    String execute(String method, String url, Map<String, String> headers, String body);

}
