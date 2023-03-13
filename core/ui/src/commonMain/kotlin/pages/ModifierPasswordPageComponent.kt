package pages

import ModelState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate

class ModifierPasswordPageComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { ModifierPasswordModelState() }
}

class ModifierPasswordModelState : ModelState() {

}