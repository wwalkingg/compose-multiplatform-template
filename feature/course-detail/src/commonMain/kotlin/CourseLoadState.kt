sealed interface CourseLoadState {
    object Loading : CourseLoadState
    data class Success(val course: Course) : CourseLoadState
    object Error : CourseLoadState
}