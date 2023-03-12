package pages

import ModelState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate

class FindPartnerPageComponent(componentContext: ComponentContext):ComponentContext by componentContext{
    val modelState = instanceKeeper.getOrCreate { FindPartnerModelState() }
}
class FindPartnerModelState:ModelState(){

}