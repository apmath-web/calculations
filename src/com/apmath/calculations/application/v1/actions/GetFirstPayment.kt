package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Mixed
import com.apmath.calculations.application.v1.exceptions.BadRequestException
import com.apmath.calculations.application.v1.exceptions.BadRequestValidationException
import com.apmath.calculations.application.v1.mappers.incoming.PresentLoanWithPaymentMapper
import com.apmath.calculations.application.v1.mappers.outgoing.PaymentWithInternalDataMapper
import com.apmath.calculations.application.v1.validators.LoanBuilder
import com.apmath.calculations.application.v1.validators.MixedBuilder
import com.apmath.calculations.application.v1.validators.PaymentBuilder
import com.apmath.calculations.domain.payments.payment.PaymentServiceInterface
import com.apmath.calculations.domain.payments.payment.exceptions.*
import com.apmath.validation.simple.NullableValidator
import com.apmath.validation.simple.RequiredValidator
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetFirstPayment(paymentService: PaymentServiceInterface) {
    val mixed = receive<Mixed>()

    val loanBuilder = LoanBuilder()
        .prepend("amount", RequiredValidator())
        .prepend("term", RequiredValidator())
        .prepend("interest", RequiredValidator())
        .prepend("date", RequiredValidator())
        .prepend("rounding", RequiredValidator())
        .prepend("regularPaymentAmount", RequiredValidator())
        .prepend("remainingTerm", NullableValidator(true)) as LoanBuilder

    val paymentBuilder = PaymentBuilder()
        .prepend("date", RequiredValidator())
        .prepend("amount", RequiredValidator())
        .prepend("remainCreditBody", NullableValidator(true))
        .prepend("body", NullableValidator(true))
        .prepend("percent", NullableValidator(true))
        .prepend("fullEarlyRepayment", NullableValidator(true)) as PaymentBuilder

    val validator = MixedBuilder(loanBuilder, PaymentBuilder(), paymentBuilder)
        .prepend("loan", RequiredValidator())
        .prepend("payment", RequiredValidator())
        .prepend("lastPayment", NullableValidator(true))
        .build()

    if (!validator.validate(mixed)) {
        throw BadRequestValidationException(validator)
    }

    val presentLoanWithPayment = PresentLoanWithPaymentMapper().map(mixed.loan!!, mixed.payment!!)

    try {
        val paymentWithInternalData = paymentService.writeOf(presentLoanWithPayment)
        respond(PaymentWithInternalDataMapper().map(paymentWithInternalData))
    } catch (e: CreditAlreadyPaidException) {
        throw BadRequestException("Credit already paid")
    } catch (e: PaymentDateMoreThanNextPaymentDateException) {
        throw BadRequestException("Payment date cannot be less than last payment date")
    } catch (e: PaymentDateMoreThanNextPaymentDateException) {
        throw BadRequestException("Payment date cannot be more than next payment date")
    } catch (e: PaymentLessThanMinimalException) {
        throw BadRequestException("Payment amount should be more than minimum of 100")
    } catch (e: PaymentLessThanRegularException) {
        throw BadRequestException("Payment amount can not be less than regular payment amount")
    } catch (e: PaymentMoreThanFullEarlyRepaimentException) {
        throw BadRequestException("Payment amount cannot be more than credit full early repaiment amount")
    }
}
