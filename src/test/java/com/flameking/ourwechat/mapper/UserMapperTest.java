package com.flameking.ourwechat.mapper;

import com.flameking.ourwechat.OurWeChatApplication;
import com.flameking.ourwechat.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {OurWeChatApplication.class})
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;
  @Test
  public void selectByExample() {
    User user = new User();
    user.setPassword("456789");
    user.setWexinId("flameking123");
    int insert = userMapper.insert(user);
  }
}