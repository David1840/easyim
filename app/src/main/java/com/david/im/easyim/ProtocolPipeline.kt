package com.david.im.easyim

import com.david.im.easyim.proto.IMessage
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender
import io.netty.handler.timeout.IdleStateHandler
import java.util.concurrent.TimeUnit

class ProtocolPipeline : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()

        pipeline.addLast("send heartbeat", IdleStateHandler(0, 15, 0, TimeUnit.SECONDS))
//
        // 半包的处理
        pipeline.addLast(ProtobufVarint32FrameDecoder())
        pipeline.addLast("proto decoder", ProtobufDecoder(IMessage.Protocol.getDefaultInstance()))
        pipeline.addLast(ProtobufVarint32LengthFieldPrepender())
        pipeline.addLast("proto encoder", ProtobufEncoder())

        pipeline.addLast(ClientHandler())
    }
}