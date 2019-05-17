package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Mixed
import com.apmath.calculations.application.v1.exceptions.BadRequestValidationException
import com.apmath.calculations.application.v1.mappers.incoming.PresentLoanMapper
import com.apmath.calculations.application.v1.mappers.outgoing.PaymentsMapper
import com.apmath.calculations.application.v1.validators.LoanBuilder
import com.apmath.calculations.application.v1.validators.MixedBuilder
import com.apmath.calculations.application.v1.validators.PaymentBuilder
import com.apmath.calculations.domain.payments.list.PaymentsServiceInterface
import com.apmath.validation.simple.NullableValidator
import com.apmath.validation.simple.RequiredValidator
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetRemainPayments(paymentsService: PaymentsServiceInterface) {
    val mixed = receive<Mixed>()

    val loanBuilder = LoanBuilder()
        .prepend("amount", NullableValidator(true))
        .prepend("term", NullableValidator(true))
        .prepend("interest", RequiredValidator())
        .prepend("date", RequiredValidator())
        .prepend("rounding", RequiredValidator())
        .prepend("regularPaymentAmount", RequiredValidator())
        .prepend("remainingTerm", NullableValidator(true)) as LoanBuilder

    val lastPaymentBuilder = PaymentBuilder()
        .prepend("date", RequiredValidator())
        .prepend("amount", NullableValidator(true))
        .prepend("remainCreditBody", RequiredValidator())
        .prepend("body", RequiredValidator())
        .prepend("percent", NullableValidator(true))
        .prepend("fullEarlyRepayment", NullableValidator(true)) as PaymentBuilder

    val validator = MixedBuilder(loanBuilder, lastPaymentBuilder, PaymentBuilder())
        .prepend("loan", RequiredValidator())
        .prepend("payment", NullableValidator(true))
        .prepend("lastPayment", RequiredValidator())
        .build()

    if (!validator.validate(mixed)) {
        throw BadRequestValidationException(validator)
    }

    val presentLoan = PresentLoanMapper().map(mixed.loan!!, mixed.lastPayment!!)

    val payments = paymentsService.generatePayments(presentLoan)

    respond(PaymentsMapper().map(payments))
}
