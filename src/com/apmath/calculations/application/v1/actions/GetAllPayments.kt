package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.*
import com.apmath.calculations.application.v1.mappers.incoming.OriginalLoanMapper
import com.apmath.calculations.application.v1.mappers.outgoing.PaymentsMapper
import com.apmath.calculations.application.v1.validators.LoanBuilder
import com.apmath.calculations.domain.payments.list.PaymentsServiceInterface
import com.apmath.validation.simple.*
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetAllPayments(paymentsService: PaymentsServiceInterface) {
    val loan = receive<Loan>()

    val validator = LoanBuilder()
        .prepend("amount", RequiredValidator())
        .prepend("term", RequiredValidator())
        .prepend("interest", RequiredValidator())
        .prepend("date", RequiredValidator())
        .prepend("rounding", NullableValidator(true))
        .prepend("regularPaymentAmount", NullableValidator(true))
        .prepend("remainingTerm", NullableValidator(true))
        .build()

    if (!validator.validate(loan)) {
        respond(validator.messages)
    }

    val originalLoan = OriginalLoanMapper().map(loan)

    val payments = paymentsService.generatePayments(originalLoan)

    respond(PaymentsMapper().map(payments))
}
