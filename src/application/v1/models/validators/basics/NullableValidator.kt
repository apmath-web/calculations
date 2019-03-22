package com.apmath.calculations.application.v1.models.validators.basics

class NullableValidator(
    private val mustBeNull: Boolean = false
): AbstractValidator() {

    private var continueValidation: Boolean = false

    override fun validate(value: Any?): Boolean {
        continueValidation = false

        if (value !== null) {
            if (mustBeNull) {
                addMessage("Must be null")

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
