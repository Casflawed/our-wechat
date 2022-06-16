package com.flameking.ourwechat.server;

import com.alibaba.fastjson.JSON;
import com.flameking.ourwechat.protocol.GroupChatRequestMessage;
import com.flameking.ourwechat.utils.ChannelGroupFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoHanlder extends ChannelInboundHandlerAdapter {
  // 服务器方的Socket连接、关闭握手
  private WebSocketServerHandshaker handshaker;

  /**
   * 客户端主动连接到服务器后，该方法被触发
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    SocketChannel channel = (SocketChannel) ctx.channel();
    log.debug("一个客户端已经连接上服务器，地址是{}，端口号是{}",
            channel.localAddress().getHostString(), channel.localAddress().getPort());
    // 客户端一旦连接，将它加入群组
    ChannelGroupFactory.channelGroup.add(ctx.channel());
    super.channelActive(ctx);
  }

  /**
   * 客户端主动断开服务器时，该方法触发
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.debug("位置是{}的客户端断开连接", ctx.channel().localAddress().toString());
    // 客户端一旦断开连接，从群聊中删除
    ChannelGroupFactory.channelGroup.remove(ctx.channel());
    super.channelInactive(ctx);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    // 如果消息对象属于http请求
    if (msg instanceof FullHttpRequest) {

      FullHttpRequest httpRequest = (FullHttpRequest) msg;

      // 如果消息解码失败
      if (!httpRequest.decoderResult().isSuccess()) {

        DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);

        // 返回消息状态码和消息提示信息给客户端
        if (httpResponse.status().code() != 200) {
          ByteBuf buf = Unpooled.copiedBuffer(httpResponse.status().toString(), CharsetUtil.UTF_8);
          httpResponse.content().writeBytes(buf);
          buf.release();
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(httpResponse);
        if (httpResponse.status().code() != 200) {
          f.addListener(ChannelFutureListener.CLOSE);
        }

        return;
      }

      // websocket握手
      WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws:/" + ctx.channel() + "/websocket", null, false);
      handshaker = wsFactory.newHandshaker(httpRequest);


      if (null == handshaker) {
        WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
      } else {
        handshaker.handshake(ctx.channel(), httpRequest);
      }

      return;
    }

    //ws
    if (msg instanceof WebSocketFrame) {

      WebSocketFrame webSocketFrame = (WebSocketFrame) msg;

      //关闭请求
      if (webSocketFrame instanceof CloseWebSocketFrame) {
        handshaker.close(ctx.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
        return;
      }

      //ping请求
      if (webSocketFrame instanceof PingWebSocketFrame) {
        ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
        return;
      }

      //只支持文本格式，不支持二进制消息
      if (!(webSocketFrame instanceof TextWebSocketFrame)) {
        throw new Exception("仅支持文本格式");
      }

      String request = ((TextWebSocketFrame) webSocketFrame).text();
      log.debug("服务端收到：{}", request);

      GroupChatRequestMessage groupChatRequestMessage = JSON.parseObject(request, GroupChatRequestMessage.class);
      //群发消息
//      TextWebSocketFrame textWebSocketFrame = MsgUtil.buildMsgAll(ctx.channel().id().toString(), groupChatRequestMessage.getMsgInfo());
//      ChannelGroupFactory.channelGroup.writeAndFlush(textWebSocketFrame);

    }

  }

  /**
   * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
    log.debug("异常信息：\r\n{}", cause.getMessage());
  }

}
