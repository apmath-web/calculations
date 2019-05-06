package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.*
import com.apmath.calculations.domain.payments.list.PaymentsServiceInterface
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetRemainPayments(paymentsService: PaymentsServiceInterface) {
    val mixed = receive<Mixed>()
    val userId = getUserId(request)
    // validate, map, process
    respond(arrayListOf(Payment(), Payment()))
}
