package com.apmath.calculations.application.v1.exceptions

import io.ktor.http.HttpStatusCode

abstract class ApiException(status: HttpStatusCode, message: String) : Exception(message) {
    val status: HttpStatusCode = status
}
