package pages

import LoginScreenState
import RootComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.instancekeeper.getOrCreate
import coroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LoginPageComponent(componentContext: ComponentContext, val navigation: StackNavigation<RootComponent.Config>) :
    ComponentContext by componentContext {
    val uiState = instanceKeeper.getOrCreate { LoginScreenState() }

    fun login() {
        coroutineScope.launch {
            MainScope().launch {
                navigation.replaceAll(RootComponent.Config.HomeConfig)
            }
        }
    }

}