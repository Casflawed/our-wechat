package com.flameking.ourwechat.controller;

import com.flameking.ourwechat.entity.JpaUser;
import com.flameking.ourwechat.service.UserService;
import com.flameking.ourwechat.support.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/find/users")
  private ResultBean<List<JpaUser>> getUsers(){
    return ResultBean.success(userService.findAllUsers());
  }
}
