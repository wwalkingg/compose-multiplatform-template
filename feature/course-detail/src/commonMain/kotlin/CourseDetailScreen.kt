import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
private fun Success(
    modifier: Modifier = Modifier,
    course: Course,
    onSubscriptClick: () -> Unit,
    onCollectClick: () -> Unit,
) {
    Column(modifier) {
        Box(
            Modifier.fillMaxWidth().height(200.dp).background(MaterialTheme.colorScheme.primaryContainer)
                .shimmerBackground()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    courseLoadState: CourseLoadState,
    onErrorClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(topBar = { TopBar(onBack = onBackClick) }) {
        when (courseLoadState) {
            CourseLoadState.Error -> {
                ErrorPage(modifier = Modifier.fillMaxSize(), errorMsg = "出错啦", onRefreshClick = onErrorClick)
            }

            CourseLoadState.Loading -> {
                Box(modifier = Modifier.fillMaxSize().shimmerBackground())
            }

            is CourseLoadState.Success -> {
                Success(
                    modifier = Modifier.fillMaxSize(),
                    course = courseLoadState.course,
                    onSubscriptClick = {},
                    onCollectClick = {},
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text("课程详细") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}