package pages

import ModelState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate

class TODOPageComponent(componentContext: ComponentContext):ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { TODOPageModelStae() }
}
class TODOPageModelStae:ModelState(){

}
