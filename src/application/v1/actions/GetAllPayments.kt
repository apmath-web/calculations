package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.models.*
import com.apmath.validation.builders.ObjectValidatorBuilder
import com.apmath.validation.simple.*
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetAllPayments() {
    val loan = receive<Loan>()

    // base builder
    val builder = ObjectValidatorBuilder()
        .append("amount", IntValidator(1, 300))
        .append("date", DateValidator())

    // override
    val validator = builder
        .prepend("amount", RequiredValidator())
        .prepend("date", RequiredValidator())
        .prepend("term", NullableValidator(true))
        .prepend("rounding", NullableValidator(true))
        .build()


    if (!validator.validate(loan)) {
        respond(validator.messages)
    }

    // validate, map, process
    respond(arrayListOf(Payment(), Payment()))
}
