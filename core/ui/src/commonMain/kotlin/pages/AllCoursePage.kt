package pages

import AllCourseScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.pop
import navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCoursePage(component: AllCoursePageComponent) {
    AllCourseScreen(modifier = Modifier.fillMaxSize(),
        courseListUIState = component.modelState.courseListUIState.collectAsState().value,
        courseSortTypeUIState = component.modelState.courseSortTypeUIState.collectAsState().value,
        sortMethod = component.modelState.sortMethod,
        onSortMethodChange = { component.modelState.sortMethod = it },
        courseSortType = component.modelState.courseType,
        onCourseSortTypeChange = { component.modelState.courseType = it },
        onErrorClick = { component.modelState.loadData() },
        onBackClick = { navigation.pop() },
        onCourseClick = { component.modelState.openCourseDetailPage(it) }
    )
}
