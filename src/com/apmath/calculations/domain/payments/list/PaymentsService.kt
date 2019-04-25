package com.apmath.calculations.domain.payments.list

import com.apmath.calculations.domain.OriginalLoanInterface
import com.apmath.calculations.domain.data.Type
import com.apmath.calculations.domain.init.LoanInitServiceInterface
import com.apmath.calculations.domain.payments.AbstractPaymentsService
import com.apmath.calculations.domain.payments.PaymentInterface
import com.apmath.calculations.domain.payments.PresentLoan
import com.apmath.calculations.domain.payments.PresentLoanInterface


class PaymentsService(loanInitService: LoanInitServiceInterface) : AbstractPaymentsService(loanInitService) {

    fun generatePayments(originalLoan: OriginalLoanInterface): List<PaymentInterface> {
        val internalLoanData = loanInitService.init(originalLoan)
        return generatePayments(
            PresentLoan(
                originalLoan.amount,
                originalLoan.interest,
                originalLoan.agreementDate,
                originalLoan.agreementDate,
                internalLoanData.rounding,
                internalLoanData.regularPaymentAmount
            )
        )
    }

    fun generatePayments(presentLoan: PresentLoanInterface): List<PaymentInterface> {
        val results: MutableList<PaymentInterface> = arrayListOf()

        var payment = getNextPayment(presentLoan, getLastPayment(presentLoan), Type.NEXT)
        results.add(payment)

        while (payment.amount != payment.fullEarlyRepayment) {
            payment = getNextPayment(presentLoan, payment, Type.REGULAR)
            results.add(payment)
        }

        return results
    }
}
