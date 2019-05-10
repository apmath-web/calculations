package com.apmath.calculations.application.v1.actions

import com.apmath.calculations.application.v1.actions.models.Info
import io.ktor.application.ApplicationCall
import io.ktor.response.respond

suspend fun ApplicationCall.v1Info() {
    respond(Info("0.0.1"))
}
