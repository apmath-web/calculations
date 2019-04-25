package com.apmath.calculations.domain.payments.payment

import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.payments.PresentLoanInterface
import java.time.LocalDate


interface PresentLoanWithPaymentInterface : PresentLoanInterface {
    val remainingTerm: Int
    val paymentDate: LocalDate
    val paymentAmount: Money
}
