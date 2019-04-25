package com.apmath.calculations.domain.init

import com.apmath.calculations.domain.OriginalLoanInterface
import com.apmath.calculations.domain.data.Money


interface LoanInitServiceInterface {

    fun init(loan: OriginalLoanInterface): InternalLoanDataInterface

    fun getAnnuityPayment(amount: Money, term: Int, interest: Int): Double
}
