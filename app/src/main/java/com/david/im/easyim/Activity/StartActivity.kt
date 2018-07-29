package com.david.im.easyim.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.david.im.easyim.IMService
import com.david.im.easyim.R
import com.david.im.easyim.SendMsgController
import com.david.im.easyim.proto.ProtocolFactory
import io.netty.channel.ChannelFutureListener
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        button2.setOnClickListener {
            SendMsgController.sendMsg(ProtocolFactory.getRegister(editText.text.toString()), ChannelFutureListener {
                runOnUiThread {
                    startActivity(Intent(this@StartActivity, MainActivity::class.java))
                    finish()
                }
            })
        }
        startService(Intent(this, IMService::class.java))
    }
}