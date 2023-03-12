import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
internal actual fun LoginLoadingDialog() {
    Dialog(onDismissRequest = {}) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clip(MaterialTheme.shapes.small).background(MaterialTheme.colorScheme.primaryContainer)
                .padding(20.dp)
        ) {
            CircularProgressIndicator()
            Text("登录中")
        }
    }
}