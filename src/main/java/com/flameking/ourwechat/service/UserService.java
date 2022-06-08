package com.flameking.ourwechat.service;

import com.flameking.ourwechat.entity.User;

public interface UserService {

    // 根据nickName查询user
    User getUserByNickname(String nickName);

    // 根据email查询user
    User getUserByEmail(String email);
}
