package com.apmath.calculations.application.v1.models.validators.basics

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class DateValidator(
    private val format: String = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$",
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
): AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        if (value !is String) {
            addMessage("Must be a string")

            return false
        }

        if (!Regex(format).matches(value)) {
            addMessage("Must be a YYYY-MM-DD date")

            return false
        }

        try {
            LocalDate.parse(value, formatter)
        } catch (e: DateTimeParseException) {
            addMessage("Must be a valid date")

            return false
        }

        return isValid!!
    }
}
