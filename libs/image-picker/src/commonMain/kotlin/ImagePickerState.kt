import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

interface ImagePickerState {
    fun pick()
}


expect class ImagePickerStateImpl : ImagePickerState {
    var painter: Painter?
}


@Composable
expect fun rememberImagePickerState(): ImagePickerState