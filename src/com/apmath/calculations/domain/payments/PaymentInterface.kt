package com.apmath.calculations.domain.payments

import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.data.Type
import java.time.LocalDate


interface PaymentInterface {
    val amount: Money
    val type: Type
    val date: LocalDate
    val percent: Money
    val body: Money
    val remainCreditBody: Money
    val fullEarlyRepayment: Money
}
