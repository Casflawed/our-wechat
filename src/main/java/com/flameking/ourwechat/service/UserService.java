package com.flameking.ourwechat.service;

import com.flameking.ourwechat.entity.User;

public interface UserService {

    // 根据微信id查询user
    User getUserByWeixinId(String weixinId);

    // 根据email查询user
    User getUserByEmail(String email);
}
