package com.flameking.ourwechat.protocol;

import io.netty.channel.ChannelId;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterChannelIdResponseMessage extends Message{
  private ChannelId id;

  public RegisterChannelIdResponseMessage(int messageType, ChannelId id) {
    super(messageType);
    this.id = id;
  }


  @Override
  public int getMessageType() {
    return RegisterChannelIdResponseMessage;
  }


}
