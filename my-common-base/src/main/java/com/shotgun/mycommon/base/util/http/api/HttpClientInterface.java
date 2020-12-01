package com.shotgun.mycommon.base.util.http.api;

public interface HttpClientInterface {

    String execute(String method,String url,String headers,String body);

}
