package com.qimu.qiapibackend.service;

import com.qimu.qiapibackend.job.DailyCheckInJob;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class redissonTest {

    @Autowired
    private DailyCheckInJob dailyCheckInJob;
    @Test
    void redis(){
        dailyCheckInJob.clearCheckInList();
    }
}
