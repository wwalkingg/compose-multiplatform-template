import kotlinx.serialization.Serializable

@Serializable
data class UserCourseResp(
    val isSubscriped: Boolean,
    val isPlaned: Boolean,
    val course: Course
)
