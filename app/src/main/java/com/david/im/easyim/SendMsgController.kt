package com.david.im.easyim

import android.util.Log
import com.david.im.easyim.proto.IMessage
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext

object SendMsgController {

    val TAG = SendMsgController::class.java.simpleName
    var channelHandlerContext: ChannelHandlerContext? = null

    fun setChannelHandler(channelHandlerContext: ChannelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext
    }

    fun sendMsg(msg: IMessage.Protocol) {
        if (channelHandlerContext != null) {
            channelHandlerContext!!.writeAndFlush(msg)
        } else {
            Log.e(TAG, "channelHandlerContext is null")
        }

    }

    fun sendMsg(msg: IMessage.Protocol, future: ChannelFutureListener) {
        if (channelHandlerContext != null) {
            channelHandlerContext!!.writeAndFlush(msg).addListener(future)
        } else {
            Log.e(TAG, "channelHandlerContext is null")
        }
    }

    fun close() {

    }
}