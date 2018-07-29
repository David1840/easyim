package com.david.im.easyim.proto

class ProtocolFactory {

    companion object {

        fun getRegister(name: String): IMessage.Protocol {
            val msg = IMessage.Register.newBuilder()
                    .setName(name).build()
            return IMessage.Protocol.newBuilder()
                    .setContentType(IMessage.ContentType.Register_INFO)
                    .setContent(msg.toByteString())
                    .build()
        }

        fun getMessage(msg: String, type: IMessage.MessageType, uuid: String?): IMessage.Protocol {
            val content = IMessage.Message.newBuilder()
                    .setMessage(msg)
                    .setType(type)
                    .setUuid(uuid)
                    .build()

            return IMessage.Protocol.newBuilder()
                    .setContentType(IMessage.ContentType.Message_INFO)
                    .setContent(content.toByteString())
                    .build()
        }
    }
}