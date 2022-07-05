package com.flameking.ourwechat.server;

import com.alibaba.fastjson.JSON;
import com.flameking.ourwechat.protocol.RegisterChannelIdMessage;
import com.flameking.ourwechat.protocol.RegisterChannelIdResponseMessage;
import com.flameking.ourwechat.session.SessionFactory;
import io.netty.channel.*;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class RegisterChannelIdHanlder extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof RegisterChannelIdMessage) {
      RegisterChannelIdMessage message = (RegisterChannelIdMessage)msg;
      // 绑定weixinId与channel的关系
      SessionFactory.getSession().bind(ctx.channel(), message.getWeixinId()); // 用户 、channel 简历关系
      NioSocketChannel channel = (NioSocketChannel)ctx.channel();
      DefaultChannelId id = (DefaultChannelId) channel.id();
      RegisterChannelIdResponseMessage responseMessage = new RegisterChannelIdResponseMessage(3, id);
      HashMap<String, String> reponseMap = new HashMap<>();
      reponseMap.put("messageType", String.valueOf(3));
      reponseMap.put("channelId", id.toString());
      String jsonString = JSON.toJSONString(reponseMap);
      TextWebSocketFrame webSocketFrame = new TextWebSocketFrame(jsonString);
      ctx.channel().writeAndFlush(webSocketFrame);
      log.debug("服务端发送RegisterChannelIdResponseMessage：{}", jsonString);

    }
    // 将消息类型处理好后，发送给其他Handlder
    super.channelRead(ctx, msg);
  }
}
