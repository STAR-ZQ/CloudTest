package com.zq.stream.schdule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {
    @Scheduled(cron = "* * * * * ?")
    public void testSchedule() {
        System.out.println("测试定时任务");
    }
}
