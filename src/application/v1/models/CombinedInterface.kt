package com.apmath.application.v1.models

interface CombinedInterface {
    val loan: LoanInterface?
    val payment: PaymentInterface?
    val lastPayment: PaymentInterface?
}
