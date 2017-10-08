package com.zhuvr.z.vr_zh.utils;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.StringRequest;

public class HttpUtils {
    private static RequestQueue queue;
    public static void get(String url, OnResponseListener<String> callBack) {
        StringRequest request = (StringRequest) NoHttp.createStringRequest(url, RequestMethod.GET);
        request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        if (queue == null) {
            queue = NoHttp.newRequestQueue();
        }

        queue.add(0, request, callBack);
    }
}
