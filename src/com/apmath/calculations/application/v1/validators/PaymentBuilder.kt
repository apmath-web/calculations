package com.apmath.calculations.application.v1.validators

import com.apmath.validation.builders.ObjectValidatorBuilder
import com.apmath.validation.simple.ComparableValidator
import com.apmath.validation.simple.DateValidator


class PaymentBuilder : ObjectValidatorBuilder() {
    init {
        append("date", DateValidator())
        append("amount", ComparableValidator(min = 1L, max = 3750000000000000L))
        append("remainCreditBody", ComparableValidator(min = 0L, max = 3000000000000000L))
        append("body", ComparableValidator(min = 0L, max = 3000000000000000L))
        append("interest", ComparableValidator(min = 0L, max = 750000000000000L))
        append("fullEarlyRepayment", ComparableValidator(min = 1L, max = 3750000000000000L))
    }
}
