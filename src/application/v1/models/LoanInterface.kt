package com.apmath.application.v1.models

import com.apmath.domain.Money

interface LoanInterface {
    val amount: Money?
    val term: Int?
    val interest: Int?
    val date: String?
    val rounding: Int?
    val regularPaymentAmount: Money?
    val remainingTerm: Int?
}
