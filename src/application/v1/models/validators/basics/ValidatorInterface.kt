package com.apmath.calculations.application.v1.models.validators.basics

interface ValidatorInterface {

    fun validate(value: Any?): Boolean

    fun continueValidation(): Boolean

    val messages: MutableList<MessageInterface>
}
