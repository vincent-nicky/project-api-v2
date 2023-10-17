package com.wsj.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import static com.wsj.apiclientsdk.utils.SignUtils.genSign;

/**
 * 调用第三方接口的客户端
 *
 */
public class MyApiClient {

    private String accessKey;
    private String secretKey;
    private String gatewayUrl;

    public MyApiClient(String accessKey, String secretKey, String gatewayUrl) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.gatewayUrl = gatewayUrl;
    }
    // TODO post 和 get 各写一个
    public String invokeInterface(String interfaceUrl, String interfaceDataJson) {
        return doInvoke(accessKey,secretKey,interfaceUrl,interfaceDataJson);
    }
    public String invokeInterface(String loginUserAk, String loginUserSk, String interfaceUrl, String interfaceDataJson) {
        return doInvoke(loginUserAk,loginUserSk,interfaceUrl,interfaceDataJson);
    }

    private String doInvokePost(String accessKey,String secretKey,String interfaceUrl,String interfaceDataJson){
        String newInterfaceDataJson = handleJSON(interfaceDataJson);
        // 开始发送请求
        String result;
        try (HttpResponse httpResponse = HttpRequest.post(gatewayUrl + interfaceUrl)
                .addHeaders(genHeaders(accessKey, secretKey, interfaceDataJson))
                .body(newInterfaceDataJson)
                .execute()) {
            System.out.println("【发送结果】" + httpResponse.getStatus());
            result = httpResponse.body();
        }
        System.out.println("【响应结果】" + result);
        return result;
    }

    private Map<String, String> genHeaders(String accessKey, String secretKey, String interfaceDataJson) {
        Map<String, String> hashMap = new HashMap<>();
        // 解析JSON字符串并将其转换为Map
        Map<String, Object> jsonMap = new Gson().fromJson(interfaceDataJson, new TypeToken<Map<String, Object>>() {
        }.getType());
        // 提取params和headers Map
        Map<String, Object> headersMap = (Map<String, Object>) jsonMap.get("headers");
        // 遍历
        if (headersMap != null){
            for (String key : headersMap.keySet()) {
                hashMap.put(key, (String) headersMap.get(key));
            }
        }
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        String encodedBody = SecureUtil.md5(accessKey + interfaceDataJson);
        hashMap.put("body", encodedBody);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(encodedBody, secretKey));
        return hashMap;
    }

    private String handleJSON(String interfaceDataJson){
        Gson gson = new Gson();
        Map<String, Object> jsonMap = gson.fromJson(interfaceDataJson, new TypeToken<Map<String, Object>>() {}.getType());
        Map<String, Object> paramsMap = (Map<String, Object>) jsonMap.get("params");
        return gson.toJson(paramsMap);
    }
}
