package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Response

abstract class AbstractValidator(
    override val field: String,
    private val continueValidationOnFail: Boolean = false
) : ValidatorInterface {

    override val response = Response()

    override fun continueValidation(): Boolean {
        if (response.messages.count() != 0) {
            return continueValidationOnFail
        }
        return true
    }
}
