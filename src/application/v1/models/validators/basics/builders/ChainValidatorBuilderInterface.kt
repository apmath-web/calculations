package com.apmath.application.v1.models.validators.basics.builders

import com.apmath.application.v1.models.validators.basics.ValidatorInterface

interface ChainValidatorBuilderInterface {
    fun append(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun prepend(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun build(): ValidatorInterface
}
