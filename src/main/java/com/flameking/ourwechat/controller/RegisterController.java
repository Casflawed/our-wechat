package com.flameking.ourwechat.controller;


import com.flameking.ourwechat.entity.User;
import com.flameking.ourwechat.service.UserService;
import com.flameking.ourwechat.support.ResultBean;
import com.flameking.ourwechat.utils.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/register")
public class RegisterController {


    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Autowired
    private UserService userService;

    /**
     *  发送邮箱验证码
     * @param email
     * @return
     */
    @GetMapping("/emailCode")
    public ResultBean sendEmailCode(@RequestParam("email") String email){
        String key = sendEmailUtil.sendEmail(email,null);
        return ResultBean.successData(key);
    }

    /**
     *   注册用户
     * @param user
     * @param key
     * @param code
     * @return
     */
    @PostMapping("/user/{key}/{code}")
    public ResultBean registerUser(@RequestBody User user,
                               @PathVariable("key") String key,
                               @PathVariable("code") String code){

        // 判断是否存在相同的email
        String email = user.getEmail();
        User user1 = userService.getUserByEmail(email);
        if(user1 != null){
            return ResultBean.error("该邮箱已被注册");
        }

        // 判断是否存在相同的wexinId
        String wexinId = user.getWexinId();
        User user2 = userService.getUserByWexinId(wexinId);
        if(user2 != null){
            return ResultBean.error("该微信ID已被注册");
        }

        // 判断验证码是否正确
        if(!sendEmailUtil.validate(key,code,true)){
            return ResultBean.error("验证码错误");
        }

        // 设置注册时间
        user.setCreateDate(new Date());

        // 把数据存储在数据库
        userService.addUser(user);

        return ResultBean.success("注册成功");
    }
}
