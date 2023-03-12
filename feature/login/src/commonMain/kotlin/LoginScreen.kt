import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    id: String,
    onIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    loginResultState: LoginResultState
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Column(
            modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("登录", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(20.dp))
            TextField(
                value = id,
                onValueChange = { onIdChange(it) },
                label = { Text("账号") },
                placeholder = { Text("请输入你的账号") }
            )
            TextField(
                value = password,
                onValueChange = { onPasswordChange(it) },
                label = { Text("密码") },
                placeholder = { Text("请输入你的密码") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = onLoginClick) {
                Text("登录")
            }
        }
    }
    if (loginResultState == LoginResultState.Loading) {
        LoginLoadingDialog()
    }
    LaunchedEffect(loginResultState) {
        when (loginResultState) {
            LoginResultState.Error -> {
                snackbarHostState.showSnackbar("出错了")
            }

            LoginResultState.Loading -> {}

            LoginResultState.None -> {}
            is LoginResultState.Success -> {
                snackbarHostState.showSnackbar("登录成功")
            }
        }
    }
}