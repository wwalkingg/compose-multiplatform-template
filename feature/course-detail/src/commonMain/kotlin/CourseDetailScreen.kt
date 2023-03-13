import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
//                SuccessTest(
//                    modifier = Modifier.fillMaxSize(),
//                    course = courseLoadState.course,
//                    onSubscriptClick = {},
//                ) {}
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