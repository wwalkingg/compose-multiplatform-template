package pages

import Course
import CourseListUIState
import CourseSortType
import CourseSortTypeUIState
import ModelState
import RootComponent
import SortMethod
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.instancekeeper.getOrCreate
import utils.error
import httpClient
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import navigation
import utils.success

class AllCoursePageComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { AllCoursePageModelState() }
}

class AllCoursePageModelState : ModelState() {

    private var _sortMethod = mutableStateOf(SortMethod.HardLevel)
    var sortMethod: SortMethod
        get() = _sortMethod.value
        set(value) {
            _sortMethod.value = value
            loadData()
        }
    private var _courseType = mutableStateOf(CourseSortType(0, "全部"))
    var courseType: CourseSortType
        get() = _courseType.value
        set(value) {
            _courseType.value = value
            loadData()
        }
    private val _courseListUIState = MutableStateFlow<CourseListUIState>(CourseListUIState.Loading)
    internal val courseListUIState = _courseListUIState.asStateFlow()

    private val _courseSortTypeUIState = MutableStateFlow<CourseSortTypeUIState>(CourseSortTypeUIState.Loading)
    internal val courseSortTypeUIState = _courseSortTypeUIState.asStateFlow()


    init {
        loadSortType()
        loadData()
    }

    private fun loadSortType() {
        coroutineScope.launch {
            _courseSortTypeUIState.emit(CourseSortTypeUIState.Loading)
            httpClient.get("/getAllCourseTypes").success<List<CourseSortType>> {
                _courseSortTypeUIState.emit(CourseSortTypeUIState.Success(listOf(CourseSortType(0, "全部")) + it))
            }
        }
    }

    fun loadData() {
        coroutineScope.launch {
            _courseListUIState.emit(CourseListUIState.Loading)
            val isLoadAllCourse = _courseType.value.id == 0
            val url =
                if (isLoadAllCourse) "/course/getAllCourses" else "/getAllCoursesByType?type=${_courseType.value.id}"
            httpClient.get(url).success<List<Course>> {
                _courseListUIState.emit(CourseListUIState.Success(it))
            }.error {
                _courseListUIState.emit(CourseListUIState.Error)
            }

        }
    }

    fun openCourseDetailPage(id: Int) {
        navigation.push(RootComponent.Config.CourseDetailConfig(id))
    }
}