package com.apmath.calculations.domain.payments

import com.apmath.calculations.domain.data.Money
import com.apmath.calculations.domain.data.Type
import com.apmath.calculations.domain.init.LoanInitServiceInterface
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import kotlin.math.floor
import kotlin.math.round


abstract class AbstractPaymentsService(
    protected val loanInitService: LoanInitServiceInterface
) {
    /**
     * Returns last Payment
     * If there is no any payments yet, create one with zeroes in a date of date
     */
    protected fun getLastPayment(loan: PresentLoanInterface): PaymentInterface {
        return Payment(
            0,
            Type.REGULAR,
            loan.lastPaymentDate,
            0,
            0,
            loan.remainAmount,
            loan.remainAmount
        )
    }

    protected fun getNextPayment(
        loan: PresentLoanInterface,
        previousPayment: PaymentInterface,
        type: Type,
        nextPaymentDate: LocalDate? = null
    ): PaymentInterface {
        val body: Money
        val date = when (nextPaymentDate) {
            null -> getNextPaymentDate(loan.agreementDate, previousPayment)
            else -> nextPaymentDate
        }
        val remainCreditBody = previousPayment.remainCreditBody - previousPayment.body
        var currentPayment = loan.regularPaymentAmount

        var percent = getPercent(loan.interest, previousPayment.date, date, remainCreditBody)

        if (currentPayment - percent < remainCreditBody) {
            body = currentPayment - percent
        } else {
            // different order and formulas for amount, body and interest calculation
            // when it is last amount
            currentPayment = floor((remainCreditBody + percent) / 10.0).toLong() * 10
            body = remainCreditBody
            percent = currentPayment - body
        }

        return Payment(
            currentPayment,
            type,
            date,
            percent,
            body,
            remainCreditBody,
            floor((remainCreditBody + percent) / 10.0).toLong() * 10
        )
    }

    /**
     * Calculates the next regular amount date
     * Uses knowledge of previous amount and date date
     * Next amount date should have same dayOfMonth as date date does
     * If amount date month have such day, last month day otherwise
     */
    protected fun getNextPaymentDate(loanAgreementDate: LocalDate, previousPayment: PaymentInterface): LocalDate {
        val paymentDayOfMonth = loanAgreementDate.dayOfMonth

        if (previousPayment.date.dayOfMonth < paymentDayOfMonth) {
            val paymentDateCandidate = previousPayment.date
            val daysInMonth = YearMonth.of(paymentDateCandidate.year, paymentDateCandidate.monthValue).lengthOfMonth()

            if (daysInMonth > paymentDateCandidate.dayOfMonth) {
                return paymentDateCandidate.withDayOfMonth(minOf(paymentDayOfMonth, daysInMonth))
            }
        }

        val paymentDateCandidate = previousPayment.date.plusMonths(1)
        val daysInMonth = YearMonth.of(paymentDateCandidate.year, paymentDateCandidate.monthValue).lengthOfMonth()

        return paymentDateCandidate.withDayOfMonth(minOf(paymentDayOfMonth, daysInMonth))
    }

    /**
     * Calculate percents according to document
     * @link http://mobile-testing.ru/loancalc/rachet_dosrochnogo_pogashenia/
     */
    protected fun getPercent(
        interest: Int,
        from: LocalDate,
        to: LocalDate,
        creditBody: Money,
        inclusiveTo: Boolean = false
    ): Money {
        if (from.year != to.year && to.dayOfMonth != 1) {
            val firstPercent = getPercent(interest, from, LocalDate.of(from.year, 12, 31), creditBody, true)
            val secondPercent = getPercent(interest, LocalDate.of(to.year, 1, 1), to, creditBody, false)
            return firstPercent + secondPercent
        }
        val percentDays = from.until(to, ChronoUnit.DAYS) + if (inclusiveTo) {
            1
        } else {
            0
        }
        val yearDays = Year.of(from.year).length()

        return round(
            creditBody.toDouble() * interest.toDouble() * percentDays.toDouble() / 100.0 / yearDays.toDouble()
        ).toLong()

    }
}
