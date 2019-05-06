package com.apmath.calculations.application.v1.mappers.outgoing

import com.apmath.calculations.application.v1.actions.models.Payment
import com.apmath.calculations.domain.payments.PaymentInterface
import java.time.format.DateTimeFormatter

class PaymentMapper {
    fun map(domainPayment: PaymentInterface): Payment {

        val payment = Payment()

        payment.run {
            date = domainPayment.date.format(DateTimeFormatter.ISO_DATE)
            amount = domainPayment.amount
            remainCreditBody = domainPayment.remainCreditBody
            body = domainPayment.body
            percent = domainPayment.percent
            fullEarlyRepayment = domainPayment.fullEarlyRepayment
        }

        return payment
    }
}