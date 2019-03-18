package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Response

abstract class AbstractValidator: ValidatorInterface {

    override val response = Response()

    companion object {
        const val MESSAGE_REQUIRED = "Is required"
    }
}
