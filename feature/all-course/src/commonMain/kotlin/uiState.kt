 sealed interface CourseListUIState {
    object Loading : CourseListUIState
    object Error : CourseListUIState
    data class Success(val list: List<Course>) : CourseListUIState
}


 sealed interface CourseSortTypeUIState {
    object Loading : CourseSortTypeUIState
    object Error : CourseSortTypeUIState
    data class Success(val list: List<CourseSortType>) : CourseSortTypeUIState
}