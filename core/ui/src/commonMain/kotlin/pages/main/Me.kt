package pages.main

import MeScreen
import UserInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Me(component: MeComponent) {
    val userInfoLoadState by component.modelState.userInfoLoadState.collectAsState()
    MeScreen(
        modifier = Modifier.fillMaxSize(),
        userInfoLoadState = userInfoLoadState
    )
}