package com.apmath.calculations.domain.payments

import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.data.Type
import java.time.LocalDate


open class Payment(
    override val amount: Money,
    override val type: Type,
    override val date: LocalDate,
    override val percent: Money,
    override val body: Money,
    override val remainCreditBody: Money,
    override val fullEarlyRepayment: Money
) : PaymentInterface
