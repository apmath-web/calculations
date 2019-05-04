package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.models.*
import com.apmath.calculations.application.v1.validators.LoanBuilder
import com.apmath.calculations.domain.payments.list.PaymentsServiceInterface
import com.apmath.validation.simple.*
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import org.modelmapper.ModelMapper

suspend fun ApplicationCall.v1GetAllPayments(paymentsService: PaymentsServiceInterface) {
    val loan = receive<Loan>()

    val validator = LoanBuilder()
        .prepend("amount", RequiredValidator())
        .prepend("date", RequiredValidator())
        .prepend("term", NullableValidator(true))
        .prepend("rounding", NullableValidator(true))
        .build()

    if (!validator.validate(loan)) {
        respond(validator.messages)
    }

    val mapper = ModelMapper()

    // validate, map, process
    respond(arrayListOf(Payment(), Payment()))
}
