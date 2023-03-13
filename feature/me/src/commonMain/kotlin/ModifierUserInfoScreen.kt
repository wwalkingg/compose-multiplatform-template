import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import org.jetbrains.skia.impl.Log
import state.Success
import state.UserInfoLoadState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifierUserInfoScreen(
    userInfoLoadState: UserInfoLoadState,
    onBackClick: () -> Unit,
    onRefresh: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopBar(onBackClick) }) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            when (userInfoLoadState) {
                UserInfoLoadState.Error -> {
                    ErrorPage(onRefreshClick = onRefresh)
                }

                UserInfoLoadState.Loading -> {
                    LoadingPage(modifier = Modifier.fillMaxSize())
                }

                is UserInfoLoadState.Success -> {
                    Success(modifier = Modifier.fillMaxSize(), userInfoLoadState.userInfo)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        title = { Text("修改用户信息") }
    )
}