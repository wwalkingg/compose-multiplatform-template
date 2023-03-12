package pages

import CourseDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.router.stack.pop
import navigation


@Composable
fun CourseDetailPage(component: CourseDetailPageComponent) {
    val loadCourseState by component.modelState.courseLoadState.collectAsState()
    CourseDetailScreen(
        courseLoadState = loadCourseState,
        onErrorClick = {
            component.modelState.loadData()
        }, onBackClick = {
            navigation.pop()
        })
}

