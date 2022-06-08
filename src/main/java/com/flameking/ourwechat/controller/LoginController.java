package com.flameking.ourwechat.controller;

import com.flameking.ourwechat.common.Const;
import com.flameking.ourwechat.entity.User;
import com.flameking.ourwechat.service.UserService;
import com.flameking.ourwechat.support.ResultBean;
import com.flameking.ourwechat.utils.RedisUtil;
import com.flameking.ourwechat.utils.SendEmailUtil;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import sun.misc.BASE64Encoder;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Autowired
    private UserService userService;


    /**
     *  获取图片验证码
     * @return
     * @throws IOException
     */
    @GetMapping("/captcha")
    public ResultBean captcha() throws IOException {

        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 300);

        HashMap<String, Object> result = new HashMap<>();
        result.put("key",key);
        result.put("captchaImg",base64Img);

        return ResultBean.successData(result);

    }


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
     *  账号登录
     * @param user
     * @param key
     * @param code
     * @return
     */
    @PostMapping("/account/{key}/{code}")
    public ResultBean loginByAccount(@RequestBody User user,
                                     @PathVariable("key") String key,
                                     @PathVariable("code") String code){

        // 判断验证码是否正确
        if( !validate(key, code, false) ){
            return ResultBean.error("验证码错误");
        }

        // 获取用户名
        String nickName = user.getNickName();
        // 获取密码
        String password = user.getPassword();

        // 判断是否为空
        if(!StringUtils.hasLength(nickName) || !StringUtils.hasLength(password)){
            return ResultBean.error("用户名不能为空");
        }

        // 获取数据库user 判断是否存在user
        User realUser = userService.getUserByNickname(nickName);
        if(realUser == null){
            return ResultBean.error("用户名或密码错误");
        }

        // 判断密码是否正确
        if(!password.equals(realUser.getPassword())){
            return ResultBean.error("用户名或密码错误");
        }

        // 去除密码返回user给前端
        realUser.setPassword("");

        return ResultBean.success("登录成功",realUser);
    }


    /**
     *  邮箱登录
     * @param email
     * @param key
     * @param code
     * @return
     */
    @PostMapping("/email/{key}/{code}")
    public ResultBean loginByEmail(@RequestParam String email,
                               @PathVariable("key") String key,
                               @PathVariable("code") String code){

        // 根据email查询user
        User user = userService.getUserByEmail(email);
        if(user == null){
            return ResultBean.error("邮箱不存在或验证码错误");
        }

        // 判断验证码是否正确
        if(!validate(key,code,true)){
            return ResultBean.error("邮箱不存在或验证码错误");
        }

        user.setPassword("");
        return ResultBean.success(user);
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
