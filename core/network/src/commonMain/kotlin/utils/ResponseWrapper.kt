package utils

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ResponseWrapper<T>(
    val code: Int,
    val data: T,
    val msg: String
)

@Serializable
data class ResponseTestWrapper(
    val code: Int,
    val msg: String,
)