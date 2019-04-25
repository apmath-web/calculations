package com.apmath.calculations.domain.init

import com.apmath.calculations.domain.OriginalLoanInterface
import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.init.exceptions.LoanAmountTooSmallException
import com.apmath.calculations.domain.exceptions.RegularPaymentLessThanMinimalException
import kotlin.math.pow


class LoanInitService() : LoanInitServiceInterface {

    override fun init(loan: OriginalLoanInterface): InternalLoanDataInterface {

        val annuityPayment = getAnnuityPayment(loan.amount, loan.term, loan.interest)
        val rounding = getRounding(annuityPayment, loan.term)
        val regularPayment = getRegularPayment(annuityPayment, rounding)

        return InternalLoanData(rounding, regularPayment)
    }

    override fun getAnnuityPayment(amount: Money, term: Int, interest: Int): Double {

        val monthPercent = interest.toDouble() / 12.0 / 100.0
        val power = (1.0 + monthPercent).pow(term)

        return amount.toDouble() * monthPercent * (power / (power - 1.0))
    }

    private fun getRounding(annuityPayment: Double, term: Int): Int {

        if (annuityPayment < 100.0) {
            throw RegularPaymentLessThanMinimalException()
        }

        arrayOf(100.0, 10.0, 1.0).forEach {
            if ((it - (annuityPayment % it)) * term.toDouble() < annuityPayment) {
                return it.toInt()
            }
        }

        throw LoanAmountTooSmallException()
    }

    private fun getRegularPayment(annuityPayment: Double, rounding: Int): Money {

        return (Math.ceil(annuityPayment / rounding.toDouble()) * rounding).toLong()
    }
}
