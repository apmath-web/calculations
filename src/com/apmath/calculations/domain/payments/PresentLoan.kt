package com.apmath.calculations.domain.payments

import com.apmath.calculations.domain.data.Money
import java.time.LocalDate


open class PresentLoan(
    override val remainAmount: Money,
    override val interest: Int,
    override val agreementDate: LocalDate,
    override val lastPaymentDate: LocalDate,
    override val rounding: Int,
    override val regularPaymentAmount: Money
) :
    PresentLoanInterface
