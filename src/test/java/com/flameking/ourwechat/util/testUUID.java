package com.flameking.ourwechat.util;

import org.junit.Test;

import java.util.UUID;


// UUID生成唯一编码
public class testUUID {
  @Test
  public void test(){

    String uId = UUID.randomUUID().toString();
    System.out.println(uId);
//    String[] split = uId.split("-");
//    for (String s : split) {
//      System.out.println(s);
//    }
    // '\0'表示空字符
    System.out.println(uId.replace('-', '\0'));

  }
}
