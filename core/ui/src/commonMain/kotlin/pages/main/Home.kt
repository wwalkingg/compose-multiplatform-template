package pages.main

import HomeScreen
import RootComponent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.push
import navigation
import settings


@Composable
fun Home(modifier: Modifier = Modifier, component: HomeComponent) {
    val swiperUIState by component.modelState.swiperUIState.collectAsState()
    val recommendsUIState by component.modelState.recommendsUIState.collectAsState()
    HomeScreen(
        modifier,
        swiperUIState,
        recommendsUIState,
        onSearchClick = { component.modelState.openSearchPage() },
        onAllCourseClick = { navigation.push(RootComponent.Config.AllCourseConfig) },
        onPersonalHealthClick = { navigation.push(RootComponent.Config.PersonalHealthConfig) },
        onTodoClick = { navigation.push(RootComponent.Config.TODOConfig) },
        onFindPartnerClick = { navigation.push(RootComponent.Config.FindPartnerConfig) }
    )
}