package com.qimu.qiapibackend.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class OssSmmsManager {

    @Value("${oss.smms.url}")
    private String url;
    @Value("${oss.smms.token}")
    private String token;

    public Map<String, Object> uploadImg(File file) {
        return doUpload(file);
    }

    public Map<String, Object> uploadImg(String filePath) {
        File file = new File(filePath);
        return doUpload(file);
    }

    private Map<String, Object> doUpload(File file) {
        // 构造 Header
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", token);
        // 构造 params
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("smfile", file);
        // 发送
        HttpResponse<String> response = Unirest.post(url + "/upload")
                .header("Authorization", token)
                .field("smfile", file)
                .asString();
        // Json转map
        Map<String, Object> mapResponse = convertJsonToMap(response.getBody());
        // 根据是否上传成功来构造返回值
        Map<String, Object> result = new HashMap<>();
        if ((Boolean) mapResponse.get("success")) {
            Map<String, Object> mapData = (Map<String, Object>) mapResponse.get("data");
            result.put("success", true);
            result.put("url", mapData.get("url"));
            result.put("hash", mapData.get("hash"));
            return result;
        } else {
            result.put("success", false);
            result.put("url", mapResponse.get("images"));
            result.put("message", mapResponse.get("code"));
            return result;
        }
    }

    public Map<String, Object> deleteImg(String imgHash) {
        // 使用unirest
        HttpResponse<String> response = Unirest.get(url + "/delete/" + imgHash)
                .header("Authorization", token)
                .asString();
        Map<String, Object> mapResponse = convertJsonToMap(response.getBody());
        // 构造返回
        Map<String, Object> result = new HashMap<>();
        result.put("success", mapResponse.get("success"));
        result.put("message", mapResponse.get("message"));
        return result;
    }

    private Map<String, Object> convertJsonToMap(String json) {
        // 使用TypeToken来保留Map<String, Object>的类型信息
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        // 将JSON字符串转换为Map
        return new Gson().fromJson(json, type);
    }
}

