package com.dsy.hangaituangou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class HangaituangouApplication {

    public static void main(String[] args) {
        SpringApplication.run(HangaituangouApplication.class, args);
        System.out.println("招聘后台服务启动成功！");
    }

}
