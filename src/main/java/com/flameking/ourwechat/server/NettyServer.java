package com.flameking.ourwechat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component("NettyServer")
public class NettyServer implements Runnable{
  private int port = 7379;

  @Override
  public void run() {
    // boss处理nio的accept事件（TCP连接）
    EventLoopGroup boss = new NioEventLoopGroup();
    // worker处理nio的I/O事件（读写事件）
    EventLoopGroup worker = new NioEventLoopGroup();

    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(boss, worker)
              .channel(NioServerSocketChannel.class)
              .localAddress("localhost", port)
              .childHandler(new MyChannelInitializer());
      // 同步等待连接
      ChannelFuture f = b.bind().sync();
      ServerSocketChannel channel = (ServerSocketChannel) f.channel();
      log.debug("netty网络服务开启，地址是{}，端口号是{}，正在等待客户端连接......",
              channel.localAddress().getHostString(), channel.localAddress().getPort());
      // 等待channel关闭
      f.channel().closeFuture().sync();
      log.debug("netty网络服务已经关闭");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      boss.shutdownGracefully();
      worker.shutdownGracefully();
      log.debug("netty网络服务关闭");
    }

  }
}
