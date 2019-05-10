package com.apmath.calculations.application.v1.validators

import com.apmath.validation.simple.NullableValidator
import com.apmath.validation.simple.RequiredValidator

class OriginalLoanBuilder : LoanBuilder() {
    init {
        prepend("amount", RequiredValidator())
        prepend("term", RequiredValidator())
        prepend("interest", RequiredValidator())
        prepend("date", RequiredValidator())
        prepend("rounding", NullableValidator(true))
        prepend("regularPaymentAmount", NullableValidator(true))
        prepend("remainingTerm", NullableValidator(true))
    }
}
