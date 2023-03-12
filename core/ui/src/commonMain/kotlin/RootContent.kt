import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import pages.LoginFeature
import pages.MainPage
import pages.main.Home

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.MainPage -> MainPage(
                modifier = Modifier.fillMaxSize(),
                modelState = child.component.modelState,
                home = { Home(component = child.component.home) },
                plan = { child.component.plan.compose() },
                person = { child.component.person.compose() },
                statistics = { child.component.statistics.compose() },
                me = { child.component.me.compose() }
            )

            is RootComponent.Child.LoginPage -> LoginFeature(
                uiState = child.component.uiState,
                modelState = child.component.modelState
            )
        }
    }
}