package com.apmath.application.v1.models.validators.basics.builders

import com.apmath.application.v1.models.validators.basics.ChainValidator
import com.apmath.application.v1.models.validators.basics.ValidatorInterface
import com.apmath.application.v1.models.validators.basics.exceptions.ValidatorFieldDiffersException

class ChainValidatorBuilder(private val field: String) : ChainValidatorBuilderInterface {
    private val validators: MutableList<ValidatorInterface> = arrayListOf()

    override fun append(validator: ValidatorInterface): ChainValidatorBuilder {
        assertField(validator.field)
        validators.add(validator)
        return this
    }

    override fun prepend(validator: ValidatorInterface): ChainValidatorBuilder {
        assertField(validator.field)
        validators.add(0, validator)
        return this
    }

    protected fun assertField(validatorField: String) {
        if (field != validatorField) {
            throw ValidatorFieldDiffersException("Trying to add $validatorField validator into $field chain")
        }
    }

    override fun build(): ValidatorInterface {
        return ChainValidator(field, validators)
    }
}
