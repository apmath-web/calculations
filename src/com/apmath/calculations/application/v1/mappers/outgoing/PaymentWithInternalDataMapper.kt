package com.apmath.calculations.application.v1.mappers.outgoing

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.application.v1.actions.models.Mixed
import com.apmath.calculations.application.v1.actions.models.Payment
import com.apmath.calculations.domain.payments.payment.PaymentWithInternalLoanDataInterface
import java.time.format.DateTimeFormatter

class PaymentWithInternalDataMapper {
    fun map(paymentWithInternalData: PaymentWithInternalLoanDataInterface): Mixed {
        val mixed = Mixed()

        mixed.run {
            loan = Loan()
            payment = Payment()
        }

        mixed.loan!!.run {
            remainingTerm = paymentWithInternalData.remainingTerm
            regularPaymentAmount = paymentWithInternalData.regularPaymentAmount
        }

        mixed.payment!!.run {
            date = paymentWithInternalData.date.format(DateTimeFormatter.ISO_DATE)
            amount = paymentWithInternalData.amount
            percent = paymentWithInternalData.percent
            body = paymentWithInternalData.body
            remainCreditBody = paymentWithInternalData.remainCreditBody
            fullEarlyRepayment = paymentWithInternalData.fullEarlyRepayment
        }

        return mixed
    }
}