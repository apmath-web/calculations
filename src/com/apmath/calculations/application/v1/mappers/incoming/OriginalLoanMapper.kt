package com.apmath.calculations.application.v1.mappers.incoming

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.application.v1.models.OriginalLoan
import com.apmath.calculations.domain.OriginalLoanInterface
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class OriginalLoanMapper {
    fun map(loan: Loan): OriginalLoanInterface {

        val agreementDate: LocalDate = LocalDate.parse(loan.date, DateTimeFormatter.ISO_DATE)

        return OriginalLoan(loan.amount!!, loan.term!!, agreementDate, loan.interest!!)
    }
}
