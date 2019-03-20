package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Message

class NullableValidator(
    field: String,
    private val mustBeNull: Boolean = false
): AbstractValidator(field) {

    private var continueValidation: Boolean = false

    override fun validate(value: Any?): Boolean {
        continueValidation = false

        if (value !== null) {
            if (mustBeNull) {
                response.addMessage(Message(field, "Must be null"))
                return false
            }
            continueValidation = true
            return true
        }
        return true
    }

    override fun continueValidation(): Boolean {
        return continueValidation
    }
}
