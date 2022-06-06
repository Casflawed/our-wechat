package com.flameking.ourwechat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
///**
// * 后端处理跨域问题，服务器对跨域请求开放访问权限，这样处理存在安全风险
// */
//@Configuration
//public class CrosConfig implements WebMvcConfigurer {
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
////  允许任何请求访问服务器
//    registry.addMapping("/**")
//            .allowedOriginPatterns("*")
//            .allowedMethods("GET", "HEAD", "POST", "DELETE", "OPTIONS", "PUT")
//            .allowCredentials(true)
//            .maxAge(3600)
//            .allowedHeaders("*");
//  }
//}
