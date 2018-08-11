package com.david.im.easyim

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.net.InetSocketAddress

class IMService : Service() {

    var mChannelFuture: ChannelFuture? = null
    var mGroup: NioEventLoopGroup? = null

    override fun onCreate() {
        super.onCreate()
        Thread(Runnable { start() }).start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun start() {
        Log.e("sd", "Connect start !")
        mGroup = NioEventLoopGroup()
        try {
            val b = Bootstrap()
            b.group(mGroup)
                    .channel(NioSocketChannel::class.java)
                    .remoteAddress(InetSocketAddress("172.18.157.43", 1088))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(ProtocolPipeline())

            mChannelFuture = b.connect().awaitUninterruptibly()
            mChannelFuture!!.channel().closeFuture().sync()
        } finally {
            mGroup!!.shutdownGracefully().sync()
        }
    }


    fun stop() {
        mChannelFuture?.let {
            it.channel().closeFuture().sync()
            mGroup?.let {
                it.shutdownGracefully().sync()
            }
        }
    }
}