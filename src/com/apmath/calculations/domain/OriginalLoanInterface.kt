package com.apmath.calculations.domain

import com.apmath.calculations.domain.data.Money
import java.time.LocalDate


interface OriginalLoanInterface {
    val amount: Money
    val term: Int
    val agreementDate: LocalDate
    val interest: Int
}
