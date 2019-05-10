package com.apmath.calculations.application.v1.validators

import com.apmath.validation.builders.ObjectValidatorBuilder


class MixedBuilder(
    loanBuilder: LoanBuilder,
    lastPaymentBuilder: PaymentBuilder,
    paymentBuilder: PaymentBuilder
) : ObjectValidatorBuilder() {
    init {
        append("loan", loanBuilder.build())
        append("lastPayment", lastPaymentBuilder.build())
        append("payment", paymentBuilder.build())
    }
}
