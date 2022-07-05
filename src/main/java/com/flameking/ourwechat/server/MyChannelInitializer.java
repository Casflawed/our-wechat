package com.flameking.ourwechat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    // http编解码器
    pipeline.addLast("http-codec", new HttpServerCodec());
    // 聚合内容
    pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
    // 大文件处理器
    pipeline.addLast("http-chunked", new ChunkedWriteHandler());
    // 自定义处理器
    pipeline.addLast("parse-messageObject", new ParseMessageObjectHanlder());
    pipeline.addLast("registry-UserChannelId", new RegisterChannelIdHanlder());
    pipeline.addLast("groupChatMessage-handler", new GroupChatMessageHandler());
  }
}
