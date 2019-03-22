package com.apmath.calculations.application.v1.models.validators

import com.apmath.calculations.application.v1.models.validators.basics.AbstractValidator
import com.apmath.calculations.application.v1.models.validators.basics.MessageInterface
import com.apmath.calculations.application.v1.models.validators.basics.ValidatorInterface
import com.apmath.calculations.application.v1.models.validators.basics.exceptions.MemberNotFoundException

class ObjectValidator(
    private val validators: Map<String, ValidatorInterface>
): AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true
        if (value == null) {
            addMessage("Must be not null")

            return false
        }

        val members = value.javaClass.kotlin.members.associateBy { it.name }

        validators.forEach {
            if (!members.containsKey(it.key)) {
                throw MemberNotFoundException("${it.key} field not found in $value")
            }

            if (!it.value.validate(members[it.key]!!.call(value))) {
                addMessages(it.key, it.value.messages)
            }
        }

        return isValid!!
    }

    protected fun addMessages(path: String, list: List<MessageInterface>)
    {
        isValid = false

        messages.addAll(list.map {
            if (it is PathMessageInterface) {
                PathMessage("$path.${it.path}", it.message)
            } else {
                PathMessage(path, it.message)
            }
        })
    }
}
