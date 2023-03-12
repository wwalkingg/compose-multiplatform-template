import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    swiperUIState: SwiperUIState,
    recommendsUIState: RecommendsUIState,
    onSearchClick: () -> Unit,
    onAllCourseClick: () -> Unit,
    onPersonalHealthClick: () -> Unit,
    onTodoClick: () -> Unit,
    onFindPartnerClick: () -> Unit
) {
    Scaffold(
        modifier,
        topBar = { TopBar(onSearchClick) },
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            // swiper
            val modifier0: Modifier = when (swiperUIState) {
                SwiperUIState.Fail -> Modifier.background(MaterialTheme.colorScheme.errorContainer)
                SwiperUIState.Loading -> Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                    .shimmerBackground()

                is SwiperUIState.Success -> Modifier
            }
            Swiper(
                modifier = modifier0.fillMaxWidth().height(120.dp),
                if (swiperUIState is SwiperUIState.Success) swiperUIState.list else emptyList()
            )
            // FunctionalMenus
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FunctionalMenus.values().forEach {
                    val onClick = {
                        when (it) {
                            FunctionalMenus.AllCourse -> onAllCourseClick()
                            FunctionalMenus.PersonalHealth -> onPersonalHealthClick()
                            FunctionalMenus.TODO -> onTodoClick()
                            FunctionalMenus.FindPartner -> onFindPartnerClick()
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer) {
                            Icon(
                                modifier = Modifier.size(42.dp).clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .clickable { onClick() }
                                    .padding(10.dp),
                                painter = painterResources(it.painterResource),
                                contentDescription = null,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                it.menuName,
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.clickable { onClick() })
                        }
                    }
                }
            }
            //
        }
    }
}



