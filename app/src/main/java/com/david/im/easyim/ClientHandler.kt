package com.david.im.easyim

import android.util.Log
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler


@ChannelHandler.Sharable
class ClientHandler : SimpleChannelInboundHandler<Any>() {

    override fun channelActive(ctx: ChannelHandlerContext) {
//        ctx.writeAndFlush("connect from client!")
        SendMsgController.setChannelHandler(ctx)
    }

    override fun channelRead0(p0: ChannelHandlerContext?, p1: Any?) {
        Log.e("ClientHandler", "get form server $p1")
    }

}