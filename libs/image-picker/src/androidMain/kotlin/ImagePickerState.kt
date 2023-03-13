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


actual class ImagePickerStateImpl() : ImagePickerState {
    actual var painter by mutableStateOf<Painter?>(null)
        internal set

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
                val inputStream = context.contentResolver.openInputStream(uri)
                val image = BitmapFactory.decodeStream(inputStream).asImageBitmap()
                imageState.painter = BitmapPainter(image)
            }
        }
    )
    imageState.insertLaunch(launcher)
    return imageState
}