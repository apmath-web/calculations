package com.apmath.calculations.application.v1.validators

import com.apmath.validation.builders.ObjectValidatorBuilder

class MixedBuilder(
    loanBuilder: LoanBuilder,
    paymentBuilder: PaymentBuilder,
    lastPaymentBuilder: PaymentBuilder
) : ObjectValidatorBuilder() {
    init {
        append("loan", loanBuilder.build())
        append("payment", paymentBuilder.build())
        append("lastPayment", lastPaymentBuilder.build())
    }
}
