package com.flameking.ourwechat.utils;

import com.flameking.ourwechat.support.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.UUID;

@Component
public class SendEmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisUtil redisUtil;

    // 发送邮件
    public String sendEmail(String mail, String message) {
        final String[] msg = {message,mail};

        String[] e_code = e_code();
        // 获取code
        String code = e_code[1];
        // 获取key
        String key = e_code[0];

        //创建匿名线程  异步实现发送邮件   使响应更快
        new Thread(new Runnable() {
            @Override
            public void run() {

                // 默认信息
                if (msg[0] == "" || msg[0] == " " || msg[0] == null) {
                    msg[0] = "您的验证码为：" + code + ",请不要泄露给他人，有效时长：5分钟";
                }

                SimpleMailMessage message = new SimpleMailMessage();
                message.setSubject("wechat - 验证码");
                message.setText(msg[0]);
                //管理员邮箱
                message.setFrom("2711554770@qq.com");
                //被发送人邮箱
                message.setTo(msg[1]);
                //开始发送
                mailSender.send(message);
            };
        }).start();

        return key;
    }

    // 随机产生key和验证码  存储在redis中  时间为300秒
    public String[] e_code(){
        String key = UUID.randomUUID().toString();
        String code = generateCode();

        redisUtil.hset(Const.EMAIL_KEY, key, code, 300);
        return new String[]{key,code};
    }

    // 随机产生4位验证码
    public String generateCode(){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     *  判断验证码是否正确
     *  根据isEmail判断是 邮箱登录or账号登录
     */
    public boolean validate(String key,String code,boolean isEmail){
        String constant = Const.CAPTCHA_KEY;
        if(isEmail){
            constant = Const.EMAIL_KEY;
        }
        // 判断key和code 是否为空
        if (!StringUtils.hasLength(code) || !StringUtils.hasLength(key)) {
            return false;
        }
        // 验证验证码的正确
        if (!code.equals(redisUtil.hget(constant, key))) {
            return false;
        }
        // 一次性使用
        redisUtil.hdel(constant, key);
        return true;
    }
}
