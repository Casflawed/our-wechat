package com.flameking.ourwechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@MapperScan("com.flameking.ourwechat.mapper")
public class OurWeChatApplication {

  public static void main(String[] args) {
    SpringApplication.run(OurWeChatApplication.class, args);
  }

}
