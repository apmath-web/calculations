package com.apmath.application.v1.models.validators.basics.builders

import com.apmath.application.v1.models.validators.basics.ChainValidator
import com.apmath.application.v1.models.validators.basics.ValidatorInterface

class ObjectValidatorBuilder(private val field: String = "") {
    private val chains: MutableMap<String, ChainValidatorBuilderInterface> = hashMapOf()

    fun append(validator: ValidatorInterface): ObjectValidatorBuilder {
        ensureChainExists(validator)

        (chains[validator.field] as ChainValidatorBuilderInterface).append(validator)

        return this
    }

    fun prepend(validator: ValidatorInterface): ObjectValidatorBuilder {
        ensureChainExists(validator)

        chains[validator.field]!!.prepend(validator)

        return this
    }

    protected fun ensureChainExists(validator: ValidatorInterface) {
        if (!chains.contains(validator.field)) {
            chains[validator.field] = ChainValidatorBuilder(validator.field)
        }
    }

    fun build(): ValidatorInterface {
        return ChainValidator(field, chains.map { it.value.build() })
    }
}
