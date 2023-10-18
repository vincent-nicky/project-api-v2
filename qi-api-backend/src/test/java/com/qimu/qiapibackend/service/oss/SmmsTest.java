package com.qimu.qiapibackend.service.oss;

import com.qimu.qiapibackend.manager.OssSmmsManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class SmmsTest {
    @Autowired
    private OssSmmsManager ossSmmsManager;
    @Test
    void uploadTest(){
        Map<String, Object> res = ossSmmsManager.uploadImg("C:\\Users\\86178\\Desktop\\file\\win11壁纸\\61 (小).jpg");
        System.out.println(res);
    }

    @Test
    void deleteTest(){
        Map<String, Object> res2 = ossSmmsManager.deleteImg("YCADqXkxUsvBw5mhFQLWNMgfIH");
        System.out.println(res2);
    }
}
