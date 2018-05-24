package com.david.im.easyim

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.timeout.IdleStateHandler
import io.netty.util.CharsetUtil
import java.util.concurrent.TimeUnit

class ProtocolPipeline : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()

//        pipeline.addLast("send heartbeat", IdleStateHandler(0, 60, 0, TimeUnit.SECONDS))
//
//        // 半包的处理
//        pipeline.addLast(ProtobufVarint32FrameDecoder())
//        pipeline.addLast("proto decoder", ProtobufDecoder())
//        pipeline.addLast(ProtobufVarint32LengthFieldPrepender())
//        pipeline.addLast("proto encoder", ProtobufEncoder())

//        pipeline.addLast("receive handler", RevHandler(
//                mOnHeartbeatListener,
//                mOnChannelClosedListener,
//                mOnRevProtoListener))

        pipeline.addLast(StringDecoder(CharsetUtil.UTF_8))
        pipeline.addLast(StringEncoder(CharsetUtil.UTF_8))
        pipeline.addLast("send heartbeat", IdleStateHandler(0, 10, 0, TimeUnit.SECONDS))
        pipeline.addLast(ClientHandler())
    }
}