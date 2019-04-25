package com.apmath.calculations.domain.init

import com.apmath.calculations.domain.data.Money


interface InternalLoanDataInterface {
    val rounding: Int
    val regularPaymentAmount: Money
}
