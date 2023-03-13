package pages

import Course
import CourseLoadState
import ModelState
import UserCourseResp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import utils.error
import httpClient
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.success

class CourseDetailPageComponent(componentContext: ComponentContext, val id: Int) :
    ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { CourseDetailPageModelState(id) }
}

class CourseDetailPageModelState(val id: Int) : ModelState() {
    private val _courseLoadState = MutableStateFlow<CourseLoadState>(CourseLoadState.Loading)
    val courseLoadState = _courseLoadState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        coroutineScope.launch {
            _courseLoadState.emit(CourseLoadState.Loading)
            httpClient.get("/filter/getCoursesById?courseId=${id}").success<UserCourseResp> {
                _courseLoadState.emit(CourseLoadState.Success(it))
            }.error {
                _courseLoadState.emit(CourseLoadState.Error)
            }
        }
    }
}
