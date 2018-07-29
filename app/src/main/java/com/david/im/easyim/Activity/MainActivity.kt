package com.david.im.easyim.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.david.im.easyim.R
import com.david.im.easyim.SendMsgController
import com.david.im.easyim.proto.IMessage
import com.david.im.easyim.proto.ProtocolFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            if (!editText2.text.isNullOrEmpty()) {
                SendMsgController.sendMsg(ProtocolFactory.getMessage(editText2.text.toString(), IMessage.MessageType.ALL, ""))
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        SendMsgController.close()
    }
}