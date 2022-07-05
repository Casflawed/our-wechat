package com.flameking.ourwechat.protocol;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GroupChatMessage extends Message{

  private String dateTime;
  private String body;
  private String channelId;
  private String weixinId;
  private String imgUrl;

  public GroupChatMessage(int messageType, String dateTime, String body, String channelId, String weixinId, String imgUrl) {
    super(messageType);
    this.dateTime = dateTime;
    this.body = body;
    this.channelId = channelId;
    this.weixinId = weixinId;
    this.imgUrl = imgUrl;
  }

  @Override
  public int getMessageType() {
    return GroupChatMessage;
  }
}
