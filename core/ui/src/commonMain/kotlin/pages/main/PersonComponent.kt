package pages.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

class PersonComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    @Composable
    fun compose(){
        Text("person")
    }
}