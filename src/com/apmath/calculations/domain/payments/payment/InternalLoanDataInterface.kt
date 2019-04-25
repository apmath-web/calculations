package com.apmath.calculations.domain.payments.payment

import com.apmath.calculations.domain.data.Money


interface InternalLoanDataInterface {
    val remainingTerm: Int
    val regularPaymentAmount: Money
}
