package com.apmath.calculations.application.v1

import com.apmath.calculations.application.v1.actions.*
import io.ktor.application.call
import io.ktor.routing.*

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

    route("v1") {
        post("payments") {
            call.v1GetAllPayments()
        }
        put("payments") {
            call.v1GetRemainPayments()
        }
        post("loan") {
            call.v1ProcessNewLoan()
        }
        post("amount") {
            call.v1GetFirstPayment()
        }
        put("amount") {
            call.v1GetNextPayment()
        }
    }
}
