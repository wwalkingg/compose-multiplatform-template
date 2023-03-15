import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import java.io.InputStream


actual class ImagePickerStateImpl() : ImagePickerState {
    internal var _inputStream = mutableStateOf<InputStream?>(null)
    actual override val inputStream = _inputStream.value
    actual override val painter: Painter?
        get() {
            if (inputStream == null) return null
            val image = BitmapFactory.decodeStream(inputStream).asImageBitmap()
            return BitmapPainter(image)
        }

    var _launcher: ManagedActivityResultLauncher<String, Uri?>? = null

    fun insertLaunch(launcher: ManagedActivityResultLauncher<String, Uri?>) {
        _launcher = launcher
    }

    override fun pick() {
        _launcher!!.launch("image/*")
    }


}

@Composable
actual fun rememberImagePickerState(): ImagePickerState {
    val context = LocalContext.current
    val imageState = remember { ImagePickerStateImpl() }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                imageState._inputStream.value = context.contentResolver.openInputStream(uri)
            }
        }
    )
    imageState.insertLaunch(launcher)
    return imageState
}