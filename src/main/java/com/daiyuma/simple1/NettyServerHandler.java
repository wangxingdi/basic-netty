package com.daiyuma.simple1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("=== 服务端收到了客户端的消息 ===");
        System.out.println("服务端读取线程:"+Thread.currentThread().getName());
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("服务端收到的消息是:"+byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址是:"+channel.remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=== 服务端消息读取完毕 ===");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("=== 服务端发生异常 ===");
        ctx.close();
    }
}
