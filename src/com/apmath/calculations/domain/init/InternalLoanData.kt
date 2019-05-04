package com.apmath.calculations.domain.init

import com.apmath.calculations.domain.data.Money


class InternalLoanData(
    override val rounding: Int,
    override val regularPaymentAmount: Money
) :
    InternalLoanDataInterface
