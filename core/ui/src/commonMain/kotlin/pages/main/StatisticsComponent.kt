package pages.main

import ModelState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.launch


class StatisticsComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val modelState = instanceKeeper.getOrCreate { StatisticsModelState() }
}

class StatisticsModelState : ModelState() {
    fun loadData(){
        coroutineScope.launch {

        }
    }
}