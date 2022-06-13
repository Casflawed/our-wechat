package com.flameking.ourwechat.exception;

public class TokenVerifyFailedException extends RuntimeException {
  public TokenVerifyFailedException() {
    super();
  }

  public TokenVerifyFailedException(String message) {
    super(message);
  }

  public TokenVerifyFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public TokenVerifyFailedException(Throwable cause) {
    super(cause);
  }

  protected TokenVerifyFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
