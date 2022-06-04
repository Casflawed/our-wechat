package com.flameking.ourwechat.support;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResultBean<T> implements Serializable {

  private static final long serialVersionUID = -8276264968757808344L;

  public static final int SUCCESS = 0;

  public static final int FAIL = -1;

  private String msg = "操作成功";

  private int code = SUCCESS;

  private T data;

  private ResultBean() {
    super();
  }

  private ResultBean(String msg, T data, int code) {
    this.msg = msg;
    this.data = data;
    this.code = code;
  }

  public static ResultBean success() {
    return success("操作成功");
  }

  public static ResultBean success(String msg) {
    return success(msg, null);
  }

  public static <T> ResultBean successData(T data) {
    return success("操作成功", data);
  }

  public static <T> ResultBean successPage(T data, Long total) {
    return success("操作成功", data);
  }

  public static <T> ResultBean success(T data) {
    return success("操作成功", data);
  }

  public static <T> ResultBean success(String msg, T data) {
    return new ResultBean(msg, data, SUCCESS);
  }

  public static ResultBean error(String msg) {
    ResultBean resultBean = new ResultBean();
    resultBean.setCode(FAIL);
    resultBean.setMsg(msg);
    return resultBean;
  }

  public static ResultBean error(String msg, Integer code) {
    ResultBean resultBean = new ResultBean();
    resultBean.setCode(code);
    resultBean.setMsg(msg);
    return resultBean;
  }
}

