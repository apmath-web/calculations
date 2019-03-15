package com.apmath.application.v1.models

import com.apmath.domain.Money

interface PaymentInterface {
    val amount: Money?
    val date: String?
    val remainCreditBody: Money?
    val percent: Money?
    val body: Money?
    val fullEarlyRepayment: Money?
}
