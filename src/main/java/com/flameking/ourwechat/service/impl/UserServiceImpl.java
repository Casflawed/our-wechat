package com.flameking.ourwechat.service.impl;

import com.flameking.ourwechat.entity.JpaUser;
import com.flameking.ourwechat.repository.UserJpaRepository;
import com.flameking.ourwechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserJpaRepository userJpaRepository;

  @Override
  public List<JpaUser> findAllUsers() {
    return userJpaRepository.findAll();
  }
}
