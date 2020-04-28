package com.ggh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 启动定时任务
@EnableScheduling
public class GghApplication {

    public static void main(String[] args) {
        SpringApplication.run(GghApplication.class, args);
        System.err.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n+++++++++++++++++++++++++++++++启动成功++++++++++++++++++++++++++++++++");
    }

}
