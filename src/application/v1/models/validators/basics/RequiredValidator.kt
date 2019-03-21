package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Message

class RequiredValidator(field: String): AbstractValidator(field) {

    override fun validate(value: Any?): Boolean {

        if (value == null) {
            response.addMessage(Message(field, "Must be not null"))

            return false
        }

        return true
    }
}
