import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        topBar = { TopBar(Modifier.fillMaxWidth(), sortMethod, onSortMethodChange, onBackClick) }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
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
            Course(course = it, onClick = onCourseClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    modifier: Modifier = Modifier,
    sortMethod: SortMethod,
    onSortMethodChange: (SortMethod) -> Unit,
    onBackClick: () -> Unit
) {
    LargeTopAppBar(
        modifier = modifier,
        title = {
            Text("全部课程")
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            var isSortMethodDropdownMenuExpanded by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.clip(MaterialTheme.shapes.medium)
                    .clickable { isSortMethodDropdownMenuExpanded = !isSortMethodDropdownMenuExpanded }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    sortMethod.string
                )
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = isSortMethodDropdownMenuExpanded,
                onDismissRequest = { isSortMethodDropdownMenuExpanded = false }) {
                SortMethod.values().forEach {
                    DropdownMenuItem(text = { Text(it.string) }, onClick = {
                        onSortMethodChange(it)
                        isSortMethodDropdownMenuExpanded = false
                    })
                }
            }
        }
    )
}
