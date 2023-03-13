import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import pages.*
import pages.main.Home
import pages.main.Me
import pages.main.Plan
import pages.main.Statistics

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Column {
        Children(
            stack = component.stack,
            modifier = modifier.weight(1f),
            animation = stackAnimation(slide()),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.MainPage -> MainPage(
                    modifier = Modifier.fillMaxSize(),
                    modelState = child.component.modelState,
                    home = { Home(component = child.component.home) },
                    plan = { Plan(child.component.plan) },
                    person = { child.component.person.compose() },
                    statistics = { Statistics(child.component.statistics) },
                    me = { Me(child.component.me) }
                )

                is RootComponent.Child.LoginPage -> LoginPage(child.component)
                is RootComponent.Child.AllCoursePage -> AllCoursePage(child.component)
                is RootComponent.Child.FindPartnerPage -> FindPartnerPage(child.component)
                is RootComponent.Child.PersonalHealthPage -> PersonalHealthPage(child.component)
                is RootComponent.Child.TODOPage -> TODOPage(child.component)
                is RootComponent.Child.CourseDetailPage -> CourseDetailPage(child.component)
                is RootComponent.Child.SearchPage -> SearchPage(child.component)
            }
        }
        if (getPlatform() == "desktop") {
            Row(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.tertiaryContainer)) {
                IconButton(onClick = { navigation.pop() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
                IconButton(onClick = { navigation.push(RootComponent.Config.LoginConfig) }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = null)
                }
                val clipboardManager = LocalClipboardManager.current
                IconButton(onClick = {
                    clipboardManager.setText(AnnotatedString(settings.getStringOrNull("token") ?: ""))
                }) {
                    Icon(Icons.Default.Info, contentDescription = null)
                }
            }
        }
    }
}

expect fun getPlatform(): String