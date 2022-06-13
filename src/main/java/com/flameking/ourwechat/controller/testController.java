package com.flameking.ourwechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/test")
@Controller
public class testController {
  @GetMapping("/one")
  public String test1(){
    return "success";
  }
}
