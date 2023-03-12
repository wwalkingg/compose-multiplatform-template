import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginScreenState,
    onLoginClick: () -> Unit
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("登录", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        TextField(
            value = uiState.id,
            onValueChange = { uiState.id = it },
            label = { Text("账号") },
            placeholder = { Text("请输入你的账号") }
        )
        TextField(
            value = uiState.password,
            onValueChange = { uiState.password = it },
            label = { Text("密码") },
            placeholder = { Text("请输入你的密码") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = onLoginClick) {
            Text("登录")
        }
    }
}