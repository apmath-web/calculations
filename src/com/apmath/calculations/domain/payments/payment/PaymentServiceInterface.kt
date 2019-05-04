package com.apmath.calculations.domain.payments.payment


interface PaymentServiceInterface {

    fun writeOf(presentLoanWithPayment: PresentLoanWithPaymentInterface): PaymentWithInternalLoanDataInterface
}
