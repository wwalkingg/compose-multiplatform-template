package pages

import LoginScreenState
import ModelState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate

class LoginPageComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val uiState = instanceKeeper.getOrCreate { LoginScreenState() }
    val modelState = instanceKeeper.getOrCreate { LoginModelState() }
}

class LoginModelState : ModelState() {
    fun login(id: String, password: String) {

    }
}