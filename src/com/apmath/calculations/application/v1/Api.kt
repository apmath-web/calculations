package com.apmath.calculations.application.v1

import com.apmath.calculations.application.v1.actions.*
import com.apmath.calculations.domain.init.LoanInitServiceInterface
import com.apmath.calculations.domain.payments.list.PaymentsServiceInterface
import com.apmath.calculations.domain.payments.payment.PaymentServiceInterface
import io.ktor.application.call
import io.ktor.routing.*
import org.koin.ktor.ext.inject


internal fun Routing.v1() {

    v1Info()
    v1Calculations()
}

private fun Routing.v1Info() {

    route("v1") {
        get("info") {
            call.v1Info()
        }
    }
}

private fun Routing.v1Calculations() {
    val loanInitService: LoanInitServiceInterface by inject()
    val paymentService: PaymentServiceInterface by inject()
    val paymentsService: PaymentsServiceInterface by inject()

    route("v1") {
        post("payments") {
            call.v1GetAllPayments(paymentsService)
        }
        put("payments") {
            call.v1GetRemainPayments(paymentsService)
        }
        post("loan") {
            call.v1ProcessNewLoan(loanInitService)
        }
        post("payment") {
            call.v1GetFirstPayment(paymentService)
        }
        put("payment") {
            call.v1GetNextPayment(paymentService)
        }
    }
}
