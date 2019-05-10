package com.apmath.calculations.application.v1.mappers.incoming

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.application.v1.actions.models.Payment
import com.apmath.calculations.domain.payments.payment.PresentLoanWithPayment
import com.apmath.calculations.domain.payments.payment.PresentLoanWithPaymentInterface
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PresentLoanWithPaymentMapper {
    fun map(loan: Loan, payment: Payment): PresentLoanWithPaymentInterface {

        val agreementDate: LocalDate = LocalDate.parse(loan.date, DateTimeFormatter.ISO_DATE)
        val paymentDate = LocalDate.parse(payment.date, DateTimeFormatter.ISO_DATE)

        return PresentLoanWithPayment(
            loan.amount!!,
            loan.interest!!,
            agreementDate,
            agreementDate,
            loan.rounding!!,
            loan.regularPaymentAmount!!,
            loan.term!!,
            paymentDate,
            payment.amount!!
        )
    }

    fun map(loan: Loan, lastPayment: Payment, payment: Payment): PresentLoanWithPaymentInterface {

        val agreementDate: LocalDate = LocalDate.parse(loan.date, DateTimeFormatter.ISO_DATE)
        val lastPaymentDate: LocalDate = LocalDate.parse(lastPayment.date, DateTimeFormatter.ISO_DATE)
        val paymentDate = LocalDate.parse(payment.date, DateTimeFormatter.ISO_DATE)

        return PresentLoanWithPayment(
            lastPayment.remainCreditBody!! - lastPayment.body!!,
            loan.interest!!,
            agreementDate,
            lastPaymentDate,
            loan.rounding!!,
            loan.regularPaymentAmount!!,
            loan.remainingTerm!!,
            paymentDate,
            payment.amount!!
        )
    }
}
