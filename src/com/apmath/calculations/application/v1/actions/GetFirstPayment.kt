package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Mixed
import com.apmath.calculations.application.v1.actions.models.Payment
import com.apmath.calculations.domain.payments.payment.PaymentServiceInterface
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetFirstPayment(paymentService: PaymentServiceInterface) {
    val mixed = receive<Mixed>()
    val userId = getUserId(request)
    // validate, map, process
    respond(Payment())
}
