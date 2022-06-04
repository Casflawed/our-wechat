package com.flameking.ourwechat.service;

import com.flameking.ourwechat.entity.JpaUser;
import java.util.List;

public interface UserService {
  List<JpaUser> findAllUsers();
}
