import androidx.compose.runtime.mutableStateListOf
import com.arkivanov.decompose.ComponentContext

actual class RootComponent actual constructor(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val windows = mutableStateListOf<Window>(Window.SingleWindow.Hello)
}