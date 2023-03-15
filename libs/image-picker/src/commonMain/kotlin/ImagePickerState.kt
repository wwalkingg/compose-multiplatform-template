import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import java.io.InputStream

interface ImagePickerState {
    val painter: Painter?
    val inputStream: InputStream?
    val isReceived: Boolean get() = inputStream != null
    fun pick()
}


expect class ImagePickerStateImpl : ImagePickerState {
    override val painter: Painter?
    override val inputStream: InputStream?
}


@Composable
expect fun rememberImagePickerState(): ImagePickerState