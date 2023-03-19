package windows

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window

@Composable
fun HelloWindow() {
    Window(onCloseRequest = {}) {
        Text("Hello KoAI")
    }
}