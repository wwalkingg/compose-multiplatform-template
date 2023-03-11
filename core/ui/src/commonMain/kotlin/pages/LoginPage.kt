package pages

import LoginScreen
import LoginScreenState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun LoginFeature(uiState: LoginScreenState, onLoginClick: () -> Unit) {
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onLoginClick = onLoginClick
    )
}