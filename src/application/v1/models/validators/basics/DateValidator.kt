package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.Message
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class DateValidator(
    field: String,
    private val format: String = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$",
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
): AbstractValidator(field) {

    override fun validate(value: Any?): Boolean {

        if (value !is String) {
            response.addMessage(Message(field, "Must be a string"))
            return false
        }

        if (!Regex(format).matches(value)) {
            response.addMessage(Message(field, "Must be a YYYY-MM-DD date"))
            return false
        }

        try {
            LocalDate.parse(value, formatter)
        } catch (e: DateTimeParseException) {
            response.addMessage(Message(field, "Must be a valid date"))
            return false
        }

        return true
    }
}
