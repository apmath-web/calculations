package com.apmath.application.v1.models.validators.basics

import com.apmath.application.v1.models.validators.basics.exceptions.MemberNotFoundException

class ObjectValidator(
    field: String,
    private val validators: Map<String, ValidatorInterface>
): AbstractValidator(field) {

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            return false
        }

        var isValid = true
        val members = value.javaClass.kotlin.members.associateBy { it.name }

        validators.forEach {
            if (!members.containsKey(it.key)) {
                throw MemberNotFoundException("${it.key} not found in $value")
            }
            if (!it.value.validate(members[it.key]!!.call())) {
                isValid = false
                response.mergeWith(it.key, it.value.response)
            }
        }

        return isValid
    }
}
