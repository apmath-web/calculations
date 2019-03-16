package com.apmath.application.v1.actions

import com.apmath.application.v1.models.*
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1GetAllPayments() {
    val loan = receive<Loan>()
    // validate, map, process
    respond(arrayListOf(Payment(), Payment()))
}
