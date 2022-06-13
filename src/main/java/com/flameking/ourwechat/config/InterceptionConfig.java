package com.flameking.ourwechat.config;

import com.flameking.ourwechat.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptionConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new JWTInterceptor())
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/login/**")
            .excludePathPatterns("/api/register/**");
  }
}
