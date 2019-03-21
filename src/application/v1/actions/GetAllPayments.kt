package com.apmath.application.v1.actions

import com.apmath.application.v1.models.*
import com.apmath.application.v1.models.validators.basics.*
import com.apmath.application.v1.models.validators.basics.builders.*
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetAllPayments() {
    val loan = receive<Loan>()

    // base builder
    val builder = ObjectValidatorBuilder()
        .append(IntValidator("amount", 1, 300))
        .append(DateValidator("date"))

    // override
    val validator = builder
        .prepend(RequiredValidator("amount"))
        .prepend(RequiredValidator("date"))
        .prepend(NullableValidator("term", true))
        .prepend(NullableValidator("rounding", true))
        .build()


    if (!validator.validate(loan)) {
        respond(validator.response.messages)
    }

    // validate, map, process
    respond(arrayListOf(Payment(), Payment()))
}
