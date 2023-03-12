package pages

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import painterResources

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun MainPage(
    modifier: Modifier = Modifier,
    modelState: MainModelState,
    home: @Composable () -> Unit,
    plan: @Composable () -> Unit,
    person: @Composable () -> Unit,
    statistics: @Composable () -> Unit,
    me: @Composable () -> Unit
) {
    val pagerState = rememberPagerState(1)
    LaunchedEffect(modelState.menuIndex) {
        pagerState.scrollToPage(modelState.menuIndex)
    }
    Scaffold(modifier, bottomBar = {
        BottomBar {
            modelState.menuIndex = it
        }
    }) {
        HorizontalPager(count = BottomMenus.values().size, state = pagerState) {
            when (it) {
                0 -> {
                    home()
                }

                1 -> {
                    plan()
                }

                2 -> {
                    person()
                }

                3 -> {
                    statistics()
                }

                else -> {
                    me()
                }

            }
        }
    }
}


@Composable
internal fun BottomBar(onIndexChange: (Int) -> Unit) {
    BottomAppBar {
        BottomMenus.values().forEach {
            val selected = false
            NavigationBarItem(
                selected = selected,
                onClick = { onIndexChange(it.ordinal) },
                icon = {
                    Icon(
                        if (selected) painterResources(it.iconSelected) else painterResources(it.icon),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = { Text(it.menuName) })
        }
    }
}

enum class BottomMenus(
    val menuName: String,
    val icon: String,
    val iconSelected: String
) {
    Home("首页", "house_simple.xml", "house_simple_fill.xml"),
    Plan("计划", "hourglass.xml", "hourglass_fill.xml"),
    Person("个人", "person.xml", "person_fill.xml"),
    Statistics("统计", "chart_bar.xml", "chart_bar_fill.xml"),
    User("我的", "identification_badge.xml", "identification_badge_fill.xml"),
}