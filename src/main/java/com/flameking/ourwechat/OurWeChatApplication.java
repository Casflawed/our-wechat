package com.flameking.ourwechat;

import com.flameking.ourwechat.server.NettyServer;
import com.flameking.ourwechat.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableWebMvc
@MapperScan("com.flameking.ourwechat.mapper")
public class OurWeChatApplication {

  public static void main(String[] args) {
    SpringApplication.run(OurWeChatApplication.class, args);
    /**
     * 解决SpringBoot监听端口和NettyServer监听端口无法同时开启的问题
     */
    NettyServer nettyServer = SpringContextHolder.getBean("NettyServer");
    // 创建线程池
    ExecutorService service = Executors.newCachedThreadPool();
    // 开启netty服务线程
    service.execute(nettyServer);
    service.shutdown();
  }

}
