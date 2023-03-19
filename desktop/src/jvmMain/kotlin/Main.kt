import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry


@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    Thread.setDefaultUncaughtExceptionHandler { t, e ->
        e.printStackTrace()
    }
    val lifecycle = LifecycleRegistry()
    // Always create the root component outside Compose on the UI thread
    val root = runOnUiThread { RootComponent(DefaultComponentContext(lifecycle)) }
    application {
        val state = rememberWindowState(size = DpSize(1000.dp, 800.dp), position = WindowPosition(Alignment.Center))
        LifecycleController(lifecycleRegistry = lifecycle, state)
        RootContent(root)
    }
}

