package com.daiyuma.simple1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=== 客户端通道已经激活 ===");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("=== 客户端收到消息 ===");
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("客户端收到的消息是:"+byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务端的地址是:"+ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("=== 客户端发生异常 ===");
        cause.printStackTrace();
        ctx.close();
    }
}
