package com.apmath.application.v1.models.validators

class Response {

    val messages: MutableList<Message> = arrayListOf()

    fun addMessage(message: Message) {
        messages.add(message)
    }

    fun mergeWith(validation: Response) {
        messages.addAll(validation.messages)
    }
}
