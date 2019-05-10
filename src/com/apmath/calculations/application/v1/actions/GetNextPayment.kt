package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Mixed
import com.apmath.calculations.application.v1.mappers.incoming.PresentLoanWithPaymentMapper
import com.apmath.calculations.application.v1.mappers.outgoing.PaymentWithInternalDataMapper
import com.apmath.calculations.application.v1.validators.LoanBuilder
import com.apmath.calculations.application.v1.validators.MixedBuilder
import com.apmath.calculations.application.v1.validators.PaymentBuilder
import com.apmath.calculations.domain.payments.payment.PaymentServiceInterface
import com.apmath.validation.simple.NullableValidator
import com.apmath.validation.simple.RequiredValidator
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetNextPayment(paymentService: PaymentServiceInterface) {
    val mixed = receive<Mixed>()

    val loanBuilder = LoanBuilder()
        .prepend("amount", NullableValidator(true))
        .prepend("term", NullableValidator(true))
        .prepend("interest", RequiredValidator())
        .prepend("date", RequiredValidator())
        .prepend("rounding", RequiredValidator())
        .prepend("regularPaymentAmount", RequiredValidator())
        .prepend("remainingTerm", RequiredValidator()) as LoanBuilder

    val paymentBuilder = PaymentBuilder()
        .prepend("date", RequiredValidator())
        .prepend("amount", RequiredValidator())
        .prepend("remainCreditBody", NullableValidator(true))
        .prepend("body", NullableValidator(true))
        .prepend("percent", NullableValidator(true))
        .prepend("fullEarlyRepayment", NullableValidator(true)) as PaymentBuilder

    val lastPaymentBuilder = PaymentBuilder()
        .prepend("date", RequiredValidator())
        .prepend("amount", NullableValidator(true))
        .prepend("remainCreditBody", RequiredValidator())
        .prepend("body", RequiredValidator())
        .prepend("percent", NullableValidator(true))
        .prepend("fullEarlyRepayment", NullableValidator(true)) as PaymentBuilder

    val validator = MixedBuilder(loanBuilder, lastPaymentBuilder, paymentBuilder)
        .prepend("loan", RequiredValidator())
        .prepend("payment", RequiredValidator())
        .prepend("lastPayment", RequiredValidator())
        .build()

    if (!validator.validate(mixed)) {
        respond(validator.messages)
    }

    val presentLoanWithPayment = PresentLoanWithPaymentMapper().map(mixed.loan!!, mixed.lastPayment!!, mixed.payment!!)

    val paymentWithInternalData = paymentService.writeOf(presentLoanWithPayment)

    respond(PaymentWithInternalDataMapper().map(paymentWithInternalData))
}
