import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun Success(modifier: Modifier = Modifier, searchResult: SearchResult) {
    if (searchResult.course.isEmpty()) {
        return EmptyPage()
    }

}

