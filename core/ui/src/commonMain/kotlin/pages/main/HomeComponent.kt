package pages.main

import ModelState
import RecommendsUIState
import RootComponent
import SwiperUIState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import navigation

class HomeComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val modelState = HomeModelState()
}

class HomeModelState : ModelState() {
    private val _swiperUIState = MutableStateFlow<SwiperUIState>(SwiperUIState.Loading)
    val swiperUIState = _swiperUIState.asStateFlow()

    private val _recommendsUIState = MutableStateFlow<RecommendsUIState>(RecommendsUIState.Loading)
    val recommendsUIState = _recommendsUIState.asStateFlow()

    init {
        coroutineScope.launch {

        }
    }

    fun openSearchPage() {
        navigation.push(RootComponent.Config.SearchConfig)
    }
}