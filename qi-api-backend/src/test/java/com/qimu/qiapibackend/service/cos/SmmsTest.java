package com.qimu.qiapibackend.service.cos;

import com.qimu.qiapibackend.manager.CosSmmsManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class SmmsTest {
    @Autowired
    private CosSmmsManager cosSmmsManager;
    @Test
    void uploadTest(){
        Map<String, Object> res = cosSmmsManager.uploadImg("C:\\Users\\86178\\Desktop\\file\\win11壁纸\\61 (小).jpg");
        System.out.println(res);
    }
}
