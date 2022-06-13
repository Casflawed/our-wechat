package com.flameking.ourwechat.interceptor;

import com.flameking.ourwechat.exception.TokenVerifyFailedException;
import com.flameking.ourwechat.utils.JWTUtil;
import com.flameking.ourwechat.utils.SpringContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("token");
    JWTUtil jwtUtil = SpringContextHolder.getBean("JWTUtil");
    try {
      // 验证token
      jwtUtil.verifyToken(token);
    } catch (Exception e) {
      e.printStackTrace();
      throw new TokenVerifyFailedException();
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}
