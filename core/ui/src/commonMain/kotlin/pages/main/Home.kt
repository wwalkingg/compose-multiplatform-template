package pages.main

import HomeScreen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier


@Composable
fun Home(modifier: Modifier = Modifier, component: HomeComponent) {
    val swiperUIState by component.modelState.swiperUIState.collectAsState()
    val recommendsUIState by component.modelState.recommendsUIState.collectAsState()
    HomeScreen(
        modifier,
        swiperUIState,
        recommendsUIState,
        onSearchClick = { component.modelState.openSearchPage() }
    )
    Text("Home")
}