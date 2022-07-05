package com.flameking.ourwechat.server;

import com.alibaba.fastjson.JSON;
import com.flameking.ourwechat.protocol.GroupChatMessage;
import com.flameking.ourwechat.protocol.RegisterChannelIdMessage;
import com.flameking.ourwechat.protocol.RegisterChannelIdResponseMessage;
import com.flameking.ourwechat.session.Session;
import com.flameking.ourwechat.session.SessionFactory;
import com.flameking.ourwechat.session.SessionMemoryImpl;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultChannelId;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Slf4j
public class GroupChatMessageHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof GroupChatMessage) {
      GroupChatMessage message = (GroupChatMessage)msg;
      // 绑定weixinId与channel的关系
      SessionMemoryImpl session = (SessionMemoryImpl) SessionFactory.getSession();
      Collection<Channel> allChannels = session.getAllChannels();
      Gson gson = new Gson();
      String toJson = gson.toJson(message);
      for (Channel channel : allChannels) {
        channel.writeAndFlush(new TextWebSocketFrame(toJson));
      }
      log.debug("服务端发送RegisterChannelIdResponseMessage：{}", toJson);
    }
    super.channelRead(ctx, msg);
  }
}
