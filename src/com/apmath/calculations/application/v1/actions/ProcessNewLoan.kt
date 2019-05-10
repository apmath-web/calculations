package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Loan
import com.apmath.calculations.application.v1.mappers.incoming.OriginalLoanMapper
import com.apmath.calculations.application.v1.mappers.outgoing.LoanMapper
import com.apmath.calculations.application.v1.validators.OriginalLoanBuilder
import com.apmath.calculations.domain.init.LoanInitServiceInterface
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1ProcessNewLoan(loanInitService: LoanInitServiceInterface) {
    val loan = receive<Loan>()

    val validator = OriginalLoanBuilder().build()

    if (!validator.validate(loan)) {
        respond(validator.messages)
    }

    val originalLoan = OriginalLoanMapper().map(loan)

    val internalLoanData = loanInitService.init(originalLoan)

    respond(LoanMapper().map(internalLoanData))
}
