import androidx.compose.runtime.Composable
import androidx.compose.runtime.key

@Composable
actual fun RootContent(component: RootComponent) {
    component.windows.forEach {
        key(it) {
            when (it) {
                is Window.SingleWindow -> {
                    when (it) {
                        Window.SingleWindow.Hello ->
                        is Window.SingleWindow.Setting -> TODO()
                    }
                }

                is Window.MultiWindow -> {

                }
            }
        }
    }

}