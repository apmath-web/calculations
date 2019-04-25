package com.apmath.calculations.domain.payments.payment

import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.data.Type
import com.apmath.calculations.domain.payments.Payment
import java.time.LocalDate


class PaymentWithInternalLoanData(
    amount: Money,
    type: Type,
    date: LocalDate,
    percent: Money,
    body: Money,
    remainCreditBody: Money,
    fullEarlyRepayment: Money,
    override val remainingTerm: Int,
    override val regularPaymentAmount: Money
) : Payment(amount, type, date, percent, body, remainCreditBody, fullEarlyRepayment),
    PaymentWithInternalLoanDataInterface
