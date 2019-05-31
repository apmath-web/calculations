package com.apmath.calculations.domain.payments.payment

import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.data.Type
import com.apmath.calculations.domain.init.LoanInitServiceInterface
import com.apmath.calculations.domain.payments.AbstractPaymentsService
import com.apmath.calculations.domain.payments.Payment
import com.apmath.calculations.domain.payments.payment.exceptions.*


class PaymentService(loanInitService: LoanInitServiceInterface) : AbstractPaymentsService(loanInitService),
    PaymentServiceInterface {

    override fun writeOf(presentLoanWithPayment: PresentLoanWithPaymentInterface): PaymentWithInternalLoanDataInterface {
        val lastPayment = getLastPayment(presentLoanWithPayment)
        var nextPayment = getNextPayment(presentLoanWithPayment, lastPayment, Type.REGULAR)
        val paymentDate = presentLoanWithPayment.paymentDate
        var isPaymentLikeRegular = false
        var isPaymentRegular = false
        var remainingTerm = presentLoanWithPayment.remainingTerm
        var regularPaymentAmount = presentLoanWithPayment.regularPaymentAmount

        when {
            presentLoanWithPayment.remainAmount <= 0
            -> throw CreditAlreadyPaidException()
            // Date
            paymentDate > nextPayment.date
            -> throw PaymentDateMoreThanNextPaymentDateException()
            paymentDate <= lastPayment.date
            -> throw PaymentDateOutdatedException()
        }

        if (nextPayment.date != paymentDate) {
            nextPayment = getNextPayment(presentLoanWithPayment, lastPayment, Type.REGULAR, paymentDate)
        } else {
            isPaymentLikeRegular = true
            if (presentLoanWithPayment.paymentAmount == nextPayment.amount) {
                isPaymentRegular = true
            }
        }

        when {
            // Payment
            presentLoanWithPayment.paymentAmount < 100 && presentLoanWithPayment.paymentAmount != nextPayment.amount
            -> throw PaymentLessThanMinimalException()
            presentLoanWithPayment.paymentAmount > nextPayment.fullEarlyRepayment
            -> throw PaymentMoreThanFullEarlyRepaimentException(nextPayment.fullEarlyRepayment)
            presentLoanWithPayment.paymentAmount < presentLoanWithPayment.regularPaymentAmount
                    && presentLoanWithPayment.regularPaymentAmount < nextPayment.fullEarlyRepayment
            -> throw PaymentLessThanRegularException()
        }

        val payment = Payment(
            presentLoanWithPayment.paymentAmount,
            if (isPaymentRegular) {
                Type.REGULAR
            } else {
                Type.EARLY
            },
            presentLoanWithPayment.paymentDate,
            nextPayment.percent,
            presentLoanWithPayment.paymentAmount - nextPayment.percent,
            nextPayment.remainCreditBody,
            nextPayment.fullEarlyRepayment
        )

        if (isPaymentLikeRegular) {
            remainingTerm--
        }

        if (!isPaymentRegular) {
            val annuityPayment = this.loanInitService.getAnnuityPayment(
                payment.remainCreditBody - payment.body,
                remainingTerm,
                presentLoanWithPayment.interest
            )
            regularPaymentAmount = getRegularPayment(annuityPayment, presentLoanWithPayment.rounding)
            if (regularPaymentAmount < 100) {
                regularPaymentAmount = 100
            }
        }

        return PaymentWithInternalLoanData(
            payment.amount,
            payment.type,
            payment.date,
            payment.percent,
            payment.body,
            payment.remainCreditBody,
            payment.fullEarlyRepayment,
            remainingTerm,
            regularPaymentAmount
        )
    }

    private fun getRegularPayment(annuityPayment: Double, rounding: Int): Money {

        return (Math.ceil(annuityPayment / rounding.toDouble()) * rounding).toLong()
    }
}
