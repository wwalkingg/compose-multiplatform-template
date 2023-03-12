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
import javax.swing.SwingUtilities


fun main() {

    Thread.setDefaultUncaughtExceptionHandler { t, e ->
        e.printStackTrace()
    }
    val lifecycle = LifecycleRegistry()
    // Always create the root component outside Compose on the UI thread
    val root = runOnUiThread { RootComponent(DefaultComponentContext(lifecycle)) }
    application {
        val state = rememberWindowState(size = DpSize(300.dp, 800.dp), position = WindowPosition(Alignment.TopEnd))
        Window(onCloseRequest = ::exitApplication, state = state) {
            MaterialTheme {
                RootContent(root)
            }
        }
    }
}


internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}