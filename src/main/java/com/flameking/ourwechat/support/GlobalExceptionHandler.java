package com.flameking.ourwechat.support;

import com.flameking.ourwechat.exception.TokenVerifyFailedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = TokenVerifyFailedException.class)
  public ResultBean resolveDuplicateKeyException(TokenVerifyFailedException exception, HttpServletRequest request,
                                                   HttpServletResponse response) throws IOException {

    return ResultBean.error("token校验失败", -1);

  }
}
