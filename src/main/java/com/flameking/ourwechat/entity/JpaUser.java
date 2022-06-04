package com.flameking.ourwechat.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`USER`")
// 用户表
public class JpaUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String nickName;

  @Column(length = 16, nullable = false)
  private String password;

  @Column(length = 50)
  private String email;

  @Column(length = 50)
  private String address;

  @Column(length = 50)
  private String avatarUrl="//cdn.jsdelivr.net/gh/Casflawed/img-host@master/blog/202206021048828.jpg";
}
