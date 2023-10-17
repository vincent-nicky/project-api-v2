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
public class CosSmmsManager {

    @Value("${cos.smms.url}")
    private String url;
    @Value("${cos.smms.token}")
    private String token;

    public Map<String, Object> uploadImg(File file) {
        return doUpload(file);
    }

    public Map<String, Object> uploadImg(String filePath) {
        File file = new File(filePath);
        return doUpload(file);
    }

    public Map<String, Object> deleteImg(String imgHash) {
        // 使用unirest
        HttpResponse<String> response = Unirest.get(url + "/delete/" + imgHash)
                .header("Authorization", token)
                .asString();
        return convertJsonToMap(response.getBody());
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
        Map<String, Object> mapSource = convertJsonToMap(response.getBody());
        // 根据是否上传成功来构造返回值
        Map<String, Object> result = new HashMap<>();
        if ((Boolean) mapSource.get("success")) {
            Map<String, Object> mapData = (Map<String, Object>) mapSource.get("data");
            result.put("success", true);
            result.put("url", mapData.get("url"));
            result.put("hash", mapData.get("hash"));
            return result;
        } else {
            result.put("success", false);
            result.put("url", mapSource.get("images"));
            result.put("message", mapSource.get("code"));
            return result;
        }
    }

    private Map<String, Object> convertJsonToMap(String json) {
        // 使用TypeToken来保留Map<String, Object>的类型信息
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        // 将JSON字符串转换为Map
        return new Gson().fromJson(json, type);
    }
}

