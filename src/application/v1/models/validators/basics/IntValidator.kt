package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Message

class IntValidator(
    val field: String,
    val required: Boolean = true,
    val min: Int = Int.MIN_VALUE,
    val max: Int = Int.MAX_VALUE
): AbstractValidator() {

    override fun validate(value: Any?): Boolean {

        if (value == null) {
            if (!required) {
                return true
            }
            response.addMessage(Message(field, MESSAGE_REQUIRED))
            return false
        }

        if (value !is Int) {
            response.addMessage(Message(field, "Must be a number"))
            return false
        }

        when {
            value > max -> response.addMessage(Message(field, "Must be less than or equal $max"))
            value < min -> response.addMessage(Message(field, "Must be more than or equal $min"))
        }

        return response.messages.count() == 0
    }
}
