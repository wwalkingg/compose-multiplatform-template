package pages

import LoginResultState
import LoginScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(component: LoginPageComponent) {
    val loginResultState by component.modelState.loginResultState.collectAsState()

    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        id = component.modelState.id,
        loginResultState = loginResultState,
        password = component.modelState.password,
        onIdChange = { component.modelState.id = it },
        onPasswordChange = { component.modelState.password = it },
        onLoginClick = { component.modelState.login() }
    )
}