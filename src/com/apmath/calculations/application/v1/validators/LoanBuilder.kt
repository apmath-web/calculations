package com.apmath.calculations.application.v1.validators

import com.apmath.validation.builders.ObjectValidatorBuilder
import com.apmath.validation.simple.ComparableValidator
import com.apmath.validation.simple.DateValidator
import com.apmath.validation.simple.RangeValidator


class LoanBuilder : ObjectValidatorBuilder() {
    init {
        append("amount", ComparableValidator(min = 1L, max = 3000000000000000L))
        append("term", ComparableValidator(min = 6, max = 1200))
        append("interest", ComparableValidator(min = 1, max = 300))
        append("date", DateValidator())
        append("rounding", RangeValidator(setOf(1, 10, 100)))
        append("regularPaymentAmount", ComparableValidator(min = 100L, max = 625000000000000L))
        append("remainingTerm", ComparableValidator(min = 0, max = 1200))
    }
}
