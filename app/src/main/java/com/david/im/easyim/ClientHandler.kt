package com.david.im.easyim

import android.util.Log
import com.david.im.easyim.proto.IMessage
import com.david.im.easyim.proto.ProtocolFactory
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.timeout.IdleState
import io.netty.handler.timeout.IdleStateEvent


class ClientHandler : SimpleChannelInboundHandler<IMessage.Protocol>() {
    private val TAG = "ClientHandler"

    override fun channelActive(ctx: ChannelHandlerContext) {
        SendMsgController.setChannelHandler(ctx)
    }

    override fun channelRead0(p0: ChannelHandlerContext?, message: IMessage.Protocol) {
        Log.e(TAG, "get form server $message")
        val contentType = message.contentType
        when (contentType) {
            IMessage.ContentType.HEART_BEAT -> {

            }
            IMessage.ContentType.Message_INFO -> {

            }

            IMessage.ContentType.Register_UUID -> {

            }
            else -> {

            }
        }
    }

    override fun userEventTriggered(ctx: ChannelHandlerContext?, evt: Any?) {
        if (evt is IdleStateEvent) {
            if (evt.state() == IdleState.WRITER_IDLE) {
                Log.d(TAG, "send heartbeat!")
                ctx?.writeAndFlush(ProtocolFactory.getHeartBeat())
            }
        }

        super.userEventTriggered(ctx, evt)
    }

}