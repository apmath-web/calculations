package com.apmath.calculations.domain.payments.payment.exceptions

import com.apmath.calculations.domain.data.Money


class PaymentMoreThanFullEarlyRepaimentException(val fullEarlyRepayment: Money) : Exception()
