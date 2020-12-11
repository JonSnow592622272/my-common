package com.shotgun.mycommon.base.util;

import com.shotgun.mycommon.base.util.http.OkHttpClientUtil;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

public class HttpUtils {
    /**
     * LocalDateTime工具
     */
    public static final OkHttpClientUtil HTTP_CLIENT_OK = new OkHttpClientUtil();


    public static void main(String[] args) throws IOException {
        //测试请求

        String headers =
                "POST http://mobile.guangri.net:8082/oa/oa_ehr.nsf/getehrdatanow?OpenWebService " + "HTTP/1"
                        + ".1\n" + "Accept-Encoding: gzip,deflate\n" + "Content-Type: text/xml;" + "charset"
                        + "=UTF-8\n" + "SOAPAction: \"\"\n" + "Content-Length: 228\n" + "Host: mobile" +
                        ".guangri.net:8082\n" + "Connection: Keep-Alive\n" + "User-Agent: " + "Apache" +
                        "-HttpClient/4.1.1 (java 1.5)\n" + "\n";

        String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:urn=\"urn:DefaultNamespace\">\n" + "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <urn:rescon>dept</urn:rescon>\n" + "   </soapenv:Body>\n" + "</soapenv:Envelope>";

        String post = HttpUtils.HTTP_CLIENT_OK
                .execute("post", "http://mobile.guangri.net:8082/oa/oa_ehr.nsf/getehrdatanow?OpenWebService",
                        headers, body);

        String s = StringEscapeUtils.unescapeHtml4(post);

        System.out.println(s);
    }

}
