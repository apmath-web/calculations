package com.apmath.calculations.application.v1.models

import com.apmath.calculations.domain.data.Money


class Loan {
    var amount: Money? = null
    var term: Int? = null
    var interest: Int? = null
    var date: String? = null
    var rounding: Int? = null
    var regularPaymentAmount: Money? = null
    var remainingTerm: Int? = null
}
