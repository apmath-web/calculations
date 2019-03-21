package com.apmath.application.v1.models.validators

import com.apmath.application.v1.models.validators.basics.Message

class PathMessage(override val path: String, message: String) : Message(message), PathMessageInterface
