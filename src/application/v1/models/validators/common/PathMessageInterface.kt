package com.apmath.application.v1.models.validators

import com.apmath.application.v1.models.validators.basics.MessageInterface

interface PathMessageInterface : MessageInterface {
    val path: String
}
