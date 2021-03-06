package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.application.v1.exceptions.BadRequestException
import com.apmath.calculations.application.v1.exceptions.BadRequestValidationException
import com.apmath.calculations.application.v1.mappers.incoming.OriginalLoanMapper
import com.apmath.calculations.application.v1.mappers.outgoing.LoanMapper
import com.apmath.calculations.application.v1.validators.OriginalLoanBuilder
import com.apmath.calculations.domain.init.LoanInitServiceInterface
import com.apmath.calculations.domain.init.exceptions.LoanAmountTooSmallException
import com.apmath.calculations.domain.init.exceptions.RegularPaymentLessThanMinimalException
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1ProcessNewLoan(loanInitService: LoanInitServiceInterface) {
    val loan = receive<Loan>()

    val validator = OriginalLoanBuilder().build()

    if (!validator.validate(loan)) {
        throw BadRequestValidationException(validator)
    }

    val originalLoan = OriginalLoanMapper().map(loan)

    try {
        val internalLoanData = loanInitService.init(originalLoan)
        respond(LoanMapper().map(internalLoanData))
    } catch (e: RegularPaymentLessThanMinimalException) {
        throw BadRequestException("Loan payment amount should be more than minimum of 100")
    } catch (e: LoanAmountTooSmallException) {
        throw BadRequestException("Too small Loan amount for concrete term")
    }
}
