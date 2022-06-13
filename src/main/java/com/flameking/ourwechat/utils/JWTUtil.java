package com.flameking.ourwechat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;

@Component("JWTUtil")
public class JWTUtil {

  // 时间偏移单位
  @Value("${wechat.time.unit}")
  private int TIMEOFFSETUNIT;
  // 时间偏移量
  @Value("${wechat.time.offset}")
  private int TIMEOFFSET;
  // 盐值（注意保密）
  @Value("${wechat.salt}")
  private String SALT;

  private final Calendar INSTANCE = Calendar.getInstance();

  {
    INSTANCE.add(TIMEOFFSETUNIT, TIMEOFFSET);
  }

  /**
   * 获取token
   * @param paramsMap
   * @return 返回token <br>
   *   token结构：header.payload.signature,signature通过算法和盐值加密生成，<br>
   *     用于校验token是否篡改
   *
   */
  public String getToken(HashMap<String, String> paramsMap){
    Builder builder = JWT.create();
    //header可省略（默认好像是这个：{typ:jwt, Alg:HMAC}）
    //builder.withHeader(new HashMap<>());

    // payload
    paramsMap.forEach((k, v) -> {
      builder.withClaim(k, v);
    });
    // 过期时间
    builder.withExpiresAt(INSTANCE.getTime());
    // signature
    String token = builder.sign(Algorithm.HMAC256(SALT));

    return token;
  }

  /**
   * 验证token信息
   * @param token
   * @return token验证成功，返回解码后的token信息，可用来获取payload用户参数， <br>
   *   验证失败，抛出异常，这里放在拦截器中捕获
   */
  public DecodedJWT verifyToken(String token){
    return JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
  }
}
