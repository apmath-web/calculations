package com.apmath.application.v1.models

import com.apmath.domain.Money

class Payment {
    var date: String? = null
    var amount: Money? = null
    var remainCreditBody: Money? = null
    var body: Money? = null
    var percent: Money? = null
    var fullEarlyRepayment: Money? = null
}
