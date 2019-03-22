package com.apmath.calculations.application.v1.models.validators.basics.builders

import com.apmath.calculations.application.v1.models.validators.basics.ChainValidator
import com.apmath.calculations.application.v1.models.validators.basics.ValidatorInterface

class ChainValidatorBuilder : ChainValidatorBuilderInterface {
    private val validators: MutableList<ValidatorInterface> = arrayListOf()

    override fun append(validator: ValidatorInterface): ChainValidatorBuilder {
        validators.add(validator)
        return this
    }

    override fun prepend(validator: ValidatorInterface): ChainValidatorBuilder {
        validators.add(0, validator)
        return this
    }

    override fun build(): ValidatorInterface {
        return ChainValidator(validators)
    }
}
