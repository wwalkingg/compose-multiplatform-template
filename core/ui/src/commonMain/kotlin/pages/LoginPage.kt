package pages

import LoginScreen
import LoginScreenState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun LoginFeature(modelState: LoginModelState, uiState: LoginScreenState) {
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onLoginClick = { modelState.login(uiState.id, uiState.password) }
    )
}