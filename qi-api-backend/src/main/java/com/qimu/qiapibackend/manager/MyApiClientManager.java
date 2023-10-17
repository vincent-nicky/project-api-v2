//package com.qimu.qiapibackend.manager;
//
//import com.qimu.qiapibackend.common.ErrorCode;
//import com.qimu.qiapibackend.exception.BusinessException;
//import com.wsj.apiclientsdk.client.MyApiClient;
//import com.wsj.apiclientsdk.exception.ApiException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyApiClientManager {
//
//    @Autowired
//    private MyApiClient myApiClient;
//
//    public String invokeInterface(String interfaceUrl, String interfaceParamsJson, String method) {
//        try {
//            return myApiClient.invokeInterface(interfaceUrl, interfaceParamsJson, method);
//        } catch (ApiException e) {
//            throw new BusinessException(ErrorCode.OPERATION_ERROR, "调用接口失败");
//        }
//    }
//
//    public String invokeInterface(String accessKey, String secretKey, String interfaceUrl, String interfaceParamsJson, String method) {
//        MyApiClient tempClient = new MyApiClient(accessKey, secretKey);
//        try {
//            return tempClient.invokeInterface(accessKey, secretKey, interfaceUrl, interfaceParamsJson, method);
//        } catch (ApiException e) {
//            throw new BusinessException(ErrorCode.OPERATION_ERROR, "调用接口失败");
//        }
//    }
//
//}
