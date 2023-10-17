package com.wsj.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wsj.apiclientsdk.model.ApiDataFieldRequest;

import java.util.HashMap;
import java.util.Map;

import static com.wsj.apiclientsdk.utils.SignUtils.genSign;

/**
 * 调用第三方接口的客户端
 */
public class MyApiClient {
    private final String accessKey;
    private final String secretKey;
    private final Gson gson = new Gson();

    public MyApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public Map<String, Object> invokeInterface(ApiDataFieldRequest apiDataFieldRequest) {
        return doInvoke(accessKey, secretKey, apiDataFieldRequest);
    }

    public Map<String, Object> invokeInterface(String loginUserAk, String loginUserSk, ApiDataFieldRequest apiDataFieldRequest) {
        return doInvoke(loginUserAk, loginUserSk, apiDataFieldRequest);
    }

    private Map<String, Object> doInvoke(String loginUserAk, String loginUserSk, ApiDataFieldRequest apiDataFieldRequest) {
        switch (apiDataFieldRequest.getMethod()) {
            case "GET": {
                // JSON 转 map
                Map<String, Object> paramssMap = gson.fromJson(apiDataFieldRequest.getParamsJson(), new TypeToken<Map<String, Object>>() {
                }.getType());
                HttpResponse httpResponse = HttpRequest.get(apiDataFieldRequest.getPath())
                        .addHeaders(genHeaders(loginUserAk, loginUserSk, apiDataFieldRequest.getHeadersJson()))
                        .form(paramssMap)
                        .execute();
                System.out.println("【发送结果】" + httpResponse.getStatus());
                String result = httpResponse.body();
                System.out.println("【响应结果】" + result);
                return gson.fromJson(result, new TypeToken<Map<String, Object>>() {
                }.getType());
            }
            case "POST": {
                HttpResponse httpResponse = HttpRequest.post(apiDataFieldRequest.getPath())
                        .addHeaders(genHeaders(loginUserAk, loginUserSk, apiDataFieldRequest.getHeadersJson()))
                        .body(apiDataFieldRequest.getParamsJson())
                        .execute();
                System.out.println("【发送结果】" + httpResponse.getStatus());
                String result = httpResponse.body();
                System.out.println("【响应结果】" + result);
                return gson.fromJson(result, new TypeToken<Map<String, Object>>() {
                }.getType());
            }
            default: {
                throw new RuntimeException("不支持的请求");
            }
        }
    }

    private Map<String, String> genHeaders(String accessKey, String secretKey, String headersJson) {
        Map<String, String> hashMap = new HashMap<>();
        // 解析JSON字符串并将其转换为Map
        Map<String, Object> headersMap = gson.fromJson(headersJson, new TypeToken<Map<String, Object>>() {
        }.getType());
        // 遍历
        if (headersMap != null) {
            for (String key : headersMap.keySet()) {
                hashMap.put(key, (String) headersMap.get(key));
            }
        }
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        String encodedBody = SecureUtil.md5(accessKey + headersJson);
        hashMap.put("body", encodedBody);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(encodedBody, secretKey));
        return hashMap;
    }
}
