package com.apmath.calculations.application.v1.actions

import io.ktor.request.ApplicationRequest

suspend fun getUserId(request: ApplicationRequest): Int? {

    val userHeaderKey = "userId"

    if (request.headers.contains(userHeaderKey)) {
        try {
            val userId = request.headers[userHeaderKey]?.toInt()
            return userId
        } catch (e: NumberFormatException) {

        }
    }

    return null
}
