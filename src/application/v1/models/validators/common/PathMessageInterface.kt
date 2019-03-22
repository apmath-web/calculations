package com.apmath.calculations.application.v1.models.validators

import com.apmath.calculations.application.v1.models.validators.basics.MessageInterface

interface PathMessageInterface : MessageInterface {
    val path: String
}
