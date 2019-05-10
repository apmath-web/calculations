package com.apmath.calculations.application.v1.mappers.incoming

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.application.v1.actions.models.Payment
import com.apmath.calculations.domain.payments.PresentLoan
import com.apmath.calculations.domain.payments.PresentLoanInterface
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PresentLoanMapper {
    fun map(loan: Loan, lastPayment: Payment): PresentLoanInterface {

        val agreementDate: LocalDate = LocalDate.parse(loan.date, DateTimeFormatter.ISO_DATE)
        val lastPaymentDate: LocalDate = LocalDate.parse(lastPayment.date, DateTimeFormatter.ISO_DATE)

        return PresentLoan(
            lastPayment.remainCreditBody!! - lastPayment.body!!,
            loan.interest!!,
            agreementDate,
            lastPaymentDate,
            loan.rounding!!,
            loan.regularPaymentAmount!!
        )
    }
}
