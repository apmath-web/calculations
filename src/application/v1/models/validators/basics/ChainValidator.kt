package com.apmath.application.v1.models.validators.basics

class ChainValidator(
    field: String,
    private val validators: List<ValidatorInterface>
): AbstractValidator(field) {

    override fun validate(value: Any?): Boolean {
        var isValid = true

        for (validator in validators) {
            if (!validator.validate(value)) {
                response.mergeWith(validator.response)
                isValid = false
            }
            if (!validator.continueValidation()) {
                break
            }
        }

        return isValid
    }
}
