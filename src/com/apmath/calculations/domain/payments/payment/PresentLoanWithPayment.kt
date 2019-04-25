package com.apmath.calculations.domain.payments.payment

import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.payments.PresentLoan
import java.time.LocalDate


class PresentLoanWithPayment(
    remainAmount: Money,
    interest: Int,
    agreementDate: LocalDate,
    lastPaymentDate: LocalDate,
    rounding: Int,
    regularPaymentAmount: Money,
    override val remainingTerm: Int,
    override val paymentDate: LocalDate,
    override val paymentAmount: Money
) : PresentLoan(remainAmount, interest, agreementDate, lastPaymentDate, rounding, regularPaymentAmount),
    PresentLoanWithPaymentInterface
