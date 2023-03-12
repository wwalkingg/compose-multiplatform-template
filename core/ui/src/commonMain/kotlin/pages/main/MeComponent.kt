package pages.main

import ModelState
import UserInfoLoadState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MeComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { MeModelState() }
}

class MeModelState : ModelState() {
    private val _userInfoLoadState = MutableStateFlow(UserInfoLoadState.Loading)
    val userInfoLoadState = _userInfoLoadState.asStateFlow()

}