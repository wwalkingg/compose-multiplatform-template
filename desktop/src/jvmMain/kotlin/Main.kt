import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry


fun main() {
    Thread.setDefaultUncaughtExceptionHandler { t, e ->
        e.printStackTrace()
    }
    val lifecycle = LifecycleRegistry()
    // Always create the root component outside Compose on the UI thread
    val root = runOnUiThread { RootComponent(DefaultComponentContext(lifecycle)) }
    application {
        val state = rememberWindowState(size = DpSize(340.dp, 800.dp), position = WindowPosition(Alignment.TopEnd))
        Window(onCloseRequest = ::exitApplication, state = state) {
            MaterialTheme {
                RootContent(root)
            }
        }
    }
}

