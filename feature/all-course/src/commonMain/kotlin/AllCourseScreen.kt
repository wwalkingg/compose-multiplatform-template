import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCourseScreen(
    modifier: Modifier = Modifier,
    courseListUIState: CourseListUIState,
    courseSortTypeUIState: CourseSortTypeUIState,
    sortMethod: SortMethod,
    onSortMethodChange: (SortMethod) -> Unit,
    courseSortType: CourseSortType,
    onCourseSortTypeChange: (CourseSortType) -> Unit,
    onErrorClick: () -> Unit,
    onBackClick: () -> Unit,
    onCourseClick: (id: Int) -> Unit
) {
    Scaffold(
        modifier,
//        topBar = { TopBar(Modifier.fillMaxWidth(), sortMethod, onSortMethodChange, onBackClick) }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
//            TopBarDOIT(Modifier.fillMaxWidth(), sortMethod, onSortMethodChange, onBackClick)
            when (courseSortTypeUIState) {
                CourseSortTypeUIState.Error -> {
                    Text("加载失败", modifier = Modifier.fillMaxWidth())
                }

                CourseSortTypeUIState.Loading -> {
                    Text("正在加载分类...", modifier = Modifier.fillMaxWidth())
                }

                is CourseSortTypeUIState.Success -> {
                    with((courseSortTypeUIState as CourseSortTypeUIState.Success)) {
                        ScrollableTabRow(list.indexOf(courseSortType), edgePadding = 20.dp) {
                            list.forEach {
                                Tab(
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    selected = it == courseSortType,
                                    onClick = { onCourseSortTypeChange(it) }) {
                                    Text(it.typeName)
                                }
                            }
                        }
                    }
                }
            }
            when (courseListUIState) {
                CourseListUIState.Error -> ErrorPage(
                    modifier = Modifier.fillMaxSize(),
                    onRefreshClick = onErrorClick
                )

                CourseListUIState.Loading -> LoadingPage(modifier = Modifier.fillMaxSize())
                is CourseListUIState.Success -> {
                    Success(
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(10.dp),
                        (courseListUIState as CourseListUIState.Success).list, onCourseClick
                    )
                }
            }
        }
    }
}

@Composable
private fun Success(modifier: Modifier = Modifier, list: List<Course>, onCourseClick: (id: Int) -> Unit) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(13.dp)) {
        list.forEach {
            Course(course = it,onClick=onCourseClick)
        }
    }
}