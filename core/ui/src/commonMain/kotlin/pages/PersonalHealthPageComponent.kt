package pages

import ModelState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate

class PersonalHealthPageComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { PersonalHealthPageModelState() }
}

class PersonalHealthPageModelState : ModelState() {

}