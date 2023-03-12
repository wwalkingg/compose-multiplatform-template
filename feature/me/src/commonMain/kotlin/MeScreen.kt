import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MeScreen(
    modifier: Modifier = Modifier,
    userInfoLoadState: UserInfoLoadState,
) {
    Column(modifier = modifier.padding(10.dp)) {
        UserInfo(modifier = Modifier.fillMaxWidth(), userInfoLoadState)
    }
}



