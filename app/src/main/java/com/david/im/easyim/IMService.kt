package com.david.im.easyim

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.netty.bootstrap.Bootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.net.InetSocketAddress

class IMService : Service() {

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
        val group = NioEventLoopGroup()
        try {
            val b = Bootstrap()
            b.group(group)
                    .channel(NioSocketChannel::class.java)
                    .remoteAddress(InetSocketAddress("172.18.157.43", 1088))
                    .handler(ProtocolPipeline())

            val f = b.connect().sync()

            f.channel().closeFuture().sync()
        } finally {
            group.shutdownGracefully().sync()
        }
    }
}