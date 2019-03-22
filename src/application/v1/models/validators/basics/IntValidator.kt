package com.apmath.calculations.application.v1.models.validators.basics

class IntValidator(
    private val min: Int = Int.MIN_VALUE,
    private val max: Int = Int.MAX_VALUE
): AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        // todo verify that Long and BigInt are pretty good with that condition
        if (value !is Int) {
            addMessage("Must be a number")

            return false
        }

        when {
            value > max -> addMessage("Must be less than or equal $max")
            value < min -> addMessage("Must be more than or equal $min")
        }

        return isValid!!
    }
}
