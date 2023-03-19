import kotlinx.serialization.Serializable

@Serializable
data class ChatData(
    val model: String = "GPT-4",
    val messages: String
)

@Serializable
data class ChatErrorResp(
    val error: CharError
)

@Serializable
data class CharError(
    val message: String,
    val type: String,
    val param: String?,
    val code: String
)
