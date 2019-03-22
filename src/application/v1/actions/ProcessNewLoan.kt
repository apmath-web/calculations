package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.models.*
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1ProcessNewLoan() {
    val loan = receive<Loan>()
    val userId = getUserId(request)
    // validate, map, process
    respond(Loan())
}
