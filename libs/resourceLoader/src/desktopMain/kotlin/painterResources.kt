import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun painterResources(res: String): Painter {
    return painterResource("drawable/${res}")
}