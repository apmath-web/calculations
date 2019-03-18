package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Response

interface ValidatorInterface {

    fun validate(value: Any?): Boolean

    val response: Response
}
