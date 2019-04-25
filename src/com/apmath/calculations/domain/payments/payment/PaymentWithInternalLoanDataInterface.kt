package com.apmath.calculations.domain.payments.payment

import com.apmath.calculations.domain.payments.PaymentInterface


interface PaymentWithInternalLoanDataInterface : PaymentInterface,
    InternalLoanDataInterface
