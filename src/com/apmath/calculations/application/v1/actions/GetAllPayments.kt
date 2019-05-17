package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.application.v1.exceptions.BadRequestValidationException
import com.apmath.calculations.application.v1.mappers.incoming.OriginalLoanMapper
import com.apmath.calculations.application.v1.mappers.outgoing.PaymentsMapper
import com.apmath.calculations.application.v1.validators.OriginalLoanBuilder
import com.apmath.calculations.domain.payments.list.PaymentsServiceInterface
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetAllPayments(paymentsService: PaymentsServiceInterface) {
    val loan = receive<Loan>()

    val validator = OriginalLoanBuilder().build()

    if (!validator.validate(loan)) {
        throw BadRequestValidationException(validator)
    }

    val originalLoan = OriginalLoanMapper().map(loan)

    val payments = paymentsService.generatePayments(originalLoan)

    respond(PaymentsMapper().map(payments))
}
