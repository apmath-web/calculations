package com.apmath.calculations.infrastructure

import com.apmath.calculations.domain.init.LoanInitService
import com.apmath.calculations.domain.init.LoanInitServiceInterface
import com.apmath.calculations.domain.payments.list.PaymentsService
import com.apmath.calculations.domain.payments.list.PaymentsServiceInterface
import com.apmath.calculations.domain.payments.payment.PaymentService
import com.apmath.calculations.domain.payments.payment.PaymentServiceInterface
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy


val template = module {
    singleBy<LoanInitServiceInterface, LoanInitService>()
    singleBy<PaymentServiceInterface, PaymentService>()
    singleBy<PaymentsServiceInterface, PaymentsService>()
}
