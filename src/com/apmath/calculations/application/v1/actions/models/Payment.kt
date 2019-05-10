package com.apmath.calculations.application.v1.actions.models

import com.apmath.calculations.domain.data.Money


class Payment {
    var date: String? = null
    var amount: Money? = null
    var remainCreditBody: Money? = null
    var body: Money? = null
    var percent: Money? = null
    var fullEarlyRepayment: Money? = null
}
