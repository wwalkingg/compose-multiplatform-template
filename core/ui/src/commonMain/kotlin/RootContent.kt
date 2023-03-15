import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) {
        CompositionLocalProvider(LocalSnackBarHostState provides snackBarHostState) {
            Column(modifier = Modifier.padding(it)) {
                Children(
                    stack = component.stack,
                    modifier = modifier.weight(1f),
                    animation = stackAnimation(slide()),
                ) {
                    when (val child = it.instance) {
                        is RootComponent.Child.MainPage -> MainPage(child.component)
                        is RootComponent.Child.LoginPage -> LoginPage(child.component)
                        is RootComponent.Child.AllCoursePage -> AllCoursePage(child.component)
                        is RootComponent.Child.FindPartnerPage -> FindPartnerPage(child.component)
                        is RootComponent.Child.PersonalHealthPage -> PersonalHealthPage(child.component)
                        is RootComponent.Child.TODOPage -> TODOPage(child.component)
                        is RootComponent.Child.CourseDetailPage -> CourseDetailPage(child.component)
                        is RootComponent.Child.SearchPage -> SearchPage(child.component)
                        is RootComponent.Child.ModifierPasswordPage -> ModifierPasswordPage(child.component)
                        is RootComponent.Child.ModifierUserInfoPage -> ModifierUserInfoPage(child.component)
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
    }
}

expect fun getPlatform(): String