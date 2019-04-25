package com.apmath.calculations.domain.payments

import com.apmath.calculations.domain.data.Money
import java.time.LocalDate


interface PresentLoanInterface {
    val remainAmount: Money
    val interest: Int
    val agreementDate: LocalDate
    val lastPaymentDate: LocalDate
    val rounding: Int
    val regularPaymentAmount: Money
}
