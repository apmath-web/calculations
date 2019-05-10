package com.apmath.calculations.application.v1.mappers.outgoing

import com.apmath.calculations.application.v1.actions.models.Payment
import com.apmath.calculations.domain.payments.PaymentInterface

class PaymentsMapper {
    fun map(domainPayments: List<PaymentInterface>): List<Payment> {

        val paymentMapper = PaymentMapper()

        return domainPayments.map { domainPayment -> paymentMapper.map(domainPayment) }
    }
}
