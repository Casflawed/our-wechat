package com.flameking.ourwechat.protocol;

import lombok.Getter;

@Getter
public class RegisterChannelIdMessage extends Message {
  private String weixinId;

  public RegisterChannelIdMessage(int messageType, String weixinId) {
    super(messageType);
    this.weixinId = weixinId;
  }


  @Override
  public int getMessageType() {
    return RegisterChannelIdMessage;
  }
}
