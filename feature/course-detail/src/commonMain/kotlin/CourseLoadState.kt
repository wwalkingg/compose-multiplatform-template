sealed interface CourseLoadState {
    object Loading : CourseLoadState
    data class Success(val course: UserCourseResp) : CourseLoadState
    object Error : CourseLoadState
}