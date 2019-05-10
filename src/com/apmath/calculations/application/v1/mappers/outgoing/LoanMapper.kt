package com.apmath.calculations.application.v1.mappers.outgoing

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.domain.init.InternalLoanDataInterface

class LoanMapper {
    fun map(loanInternalLoanData: InternalLoanDataInterface): Loan {

        val loan = Loan()

        loan.run {
            regularPaymentAmount = loanInternalLoanData.regularPaymentAmount
            rounding = loanInternalLoanData.rounding
        }

        return loan
    }
}
