package com.apmath.calculations.application.v1.models

import com.apmath.calculations.domain.OriginalLoanInterface
import com.apmath.calculations.domain.data.Money
import java.time.LocalDate

class OriginalLoan(
    override val amount: Money,
    override val term: Int,
    override val agreementDate: LocalDate,
    override val interest: Int
) : OriginalLoanInterface
