package com.apmath.calculations.domain.payments.list

import com.apmath.calculations.domain.OriginalLoanInterface
import com.apmath.calculations.domain.payments.PaymentInterface
import com.apmath.calculations.domain.payments.PresentLoanInterface


interface PaymentsServiceInterface {

    fun generatePayments(originalLoan: OriginalLoanInterface): List<PaymentInterface>

    fun generatePayments(presentLoan: PresentLoanInterface): List<PaymentInterface>
}
