package pages

import ModifierUserInfoScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.router.stack.pop
import navigation

@Composable
fun ModifierUserInfoPage(component: ModifierUserInfoPageComponent) {
    val userInfoLoadState by component.modelState.userInfoLoadState.collectAsState()
    ModifierUserInfoScreen(
        onBackClick = { navigation.pop() },
        userInfoLoadState = userInfoLoadState,
        onRefresh = { component.modelState.loadUserInfo() }
    )
}