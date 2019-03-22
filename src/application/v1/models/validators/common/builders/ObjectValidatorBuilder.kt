package com.apmath.calculations.application.v1.models.validators.basics.builders

import com.apmath.calculations.application.v1.models.validators.ObjectValidator
import com.apmath.calculations.application.v1.models.validators.basics.ValidatorInterface

class ObjectValidatorBuilder {
    private val chains: MutableMap<String, ChainValidatorBuilderInterface> = hashMapOf()

    fun append(field: String, validator: ValidatorInterface): ObjectValidatorBuilder {
        ensureChainExists(field)

        chains[field]!!.append(validator)

        return this
    }

    fun prepend(field: String, validator: ValidatorInterface): ObjectValidatorBuilder {
        ensureChainExists(field)

        chains[field]!!.prepend(validator)

        return this
    }

    protected fun ensureChainExists(field: String) {
        if (!chains.contains(field)) {
            chains[field] = ChainValidatorBuilder()
        }
    }

    fun build(): ValidatorInterface {
        return ObjectValidator(chains.mapValues { it.value.build() })
    }
}
