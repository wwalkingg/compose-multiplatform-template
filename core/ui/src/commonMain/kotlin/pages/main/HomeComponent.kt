package pages.main

import ModelState
import Recommend
import RecommendsUIState
import RootComponent
import SwiperResp
import SwiperUIState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.push
import httpClient
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import navigation
import utils.error
import utils.success

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
        loadSwiper()
        loadRecommends()
    }

    fun openSearchPage() {
        navigation.push(RootComponent.Config.SearchConfig)
    }

    fun loadSwiper() {
        coroutineScope.launch {
            _swiperUIState.emit(SwiperUIState.Loading)
            httpClient.get("/findAllSwiper").success<List<SwiperResp>> {
                _swiperUIState.emit(SwiperUIState.Success(it))
            }.error {
                _swiperUIState.emit(SwiperUIState.Fail)
            }
        }
    }

    fun loadRecommends() {
        coroutineScope.launch {
            _recommendsUIState.emit(RecommendsUIState.Loading)
            httpClient.get("/recommend").success<Recommend> {
                _recommendsUIState.emit(RecommendsUIState.Success(it))
            }.error {
                _recommendsUIState.emit(RecommendsUIState.Fail)
            }
        }
    }
}