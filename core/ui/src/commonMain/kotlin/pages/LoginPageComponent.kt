package pages

import LoginScreenState
import ModelState
import RootComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.launch
import navigation

class LoginPageComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val uiState = instanceKeeper.getOrCreate { LoginScreenState() }
    val modelState = instanceKeeper.getOrCreate { LoginModelState() }
}

class LoginModelState : ModelState() {
    fun login(id: String, password: String) {
        coroutineScope.launch {
            navigation.replaceAll(RootComponent.Config.MainConfig)
        }
    }
}