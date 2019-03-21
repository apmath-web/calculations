package com.apmath.application.v1.models.validators.basics

class RequiredValidator: AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        if (value == null) {
            addMessage("Must be not null")

            return false
        }

        return isValid!!
    }
}
