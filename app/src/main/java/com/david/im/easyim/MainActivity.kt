package com.david.im.easyim

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import android.R.attr.port
import android.R.attr.host
import android.util.Log
import io.netty.bootstrap.Bootstrap
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.EventLoopGroup
import io.netty.channel.socket.SocketChannel
import kotlinx.android.synthetic.main.activity_main.*
import java.net.InetSocketAddress


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable { start() }).start()

        button.setOnClickListener {
            SendMsgController.sendMsg("test")
        }
    }


    fun start() {
        Log.e("sd", "Connect start !")
        val group = NioEventLoopGroup()
        try {
            val b = Bootstrap()                //1
            b.group(group)                                //2
                    .channel(NioSocketChannel::class.java)            //3
                    .remoteAddress(InetSocketAddress("172.18.44.50", 1088))    //4
                    .handler(ProtocolPipeline())

            val f = b.connect().sync()        //6

            f.channel().closeFuture().sync()            //7
        } finally {
            group.shutdownGracefully().sync()            //8
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SendMsgController.close()
    }
}