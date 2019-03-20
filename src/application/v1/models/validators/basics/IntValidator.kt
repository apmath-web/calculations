package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Message

class IntValidator(
    field: String,
    private val min: Int = Int.MIN_VALUE,
    private val max: Int = Int.MAX_VALUE
): AbstractValidator(field) {

    override fun validate(value: Any?): Boolean {

        // todo verify that Long and BigInt are pretty good with that condition
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
