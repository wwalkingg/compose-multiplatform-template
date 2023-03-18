import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import buzz.lnmath.async_image.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue
import kotlin.math.sign


@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun Swiper(modifier: Modifier = Modifier, list: List<SwiperResp>) {
    val state = rememberPagerState(0)
    LaunchedEffect(Unit) {
        while (true) {
            repeat(list.size) {
                state.animateScrollToPage(it)
                delay(3000L)
            }
        }
    }
    Box(modifier) {
        HorizontalPager(
            count = list.size,
            modifier = Modifier,
            state = state
        ) { page ->
            AsyncImage(
                url = baseUrl + list[page].imageUrl,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
        HorizontalPagerIndicator(
            pagerState = state,
            modifier = Modifier.padding(bottom = 10.dp).align(Alignment.BottomCenter)
        )
    }

}

@ExperimentalPagerApi
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    pageCount: Int = pagerState.pageCount,
    pageIndexMapping: (Int) -> Int = { it },
    activeColor: Color = LocalContentColor.current,
    inactiveColor: Color = activeColor,
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,
) {
    val stateBridge = remember(pagerState) {
        object : PagerStateBridge {
            override val currentPage: Int
                get() = pagerState.currentPage
            override val currentPageOffset: Float
                get() = pagerState.currentPageOffset
        }
    }

    HorizontalPagerIndicator(
        pagerState = stateBridge,
        pageCount = pageCount,
        modifier = modifier,
        pageIndexMapping = pageIndexMapping,
        activeColor = activeColor,
        inactiveColor = inactiveColor,
        indicatorHeight = indicatorHeight,
        indicatorWidth = indicatorWidth,
        spacing = spacing,
        indicatorShape = indicatorShape
    )
}


@Composable
private fun HorizontalPagerIndicator(
    pagerState: PagerStateBridge,
    pageCount: Int,
    modifier: Modifier = Modifier,
    pageIndexMapping: (Int) -> Int = { it },
    activeColor: Color = LocalContentColor.current,
    inactiveColor: Color = activeColor,
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,
) {

    val indicatorWidthPx = LocalDensity.current.run { indicatorWidth.roundToPx() }
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val indicatorModifier = Modifier
                .size(width = indicatorWidth, height = indicatorHeight)
                .background(color = inactiveColor, shape = indicatorShape)

            repeat(pageCount) {
                Box(indicatorModifier)
            }
        }

        Box(
            Modifier
                .offset {
                    val position = pageIndexMapping(pagerState.currentPage)
                    val offset = pagerState.currentPageOffset
                    val next = pageIndexMapping(pagerState.currentPage + offset.sign.toInt())
                    val scrollPosition = ((next - position) * offset.absoluteValue + position)
                        .coerceIn(
                            0f,
                            (pageCount - 1)
                                .coerceAtLeast(0)
                                .toFloat()
                        )

                    IntOffset(
                        x = ((spacingPx + indicatorWidthPx) * scrollPosition).toInt(),
                        y = 0
                    )
                }
                .size(width = indicatorWidth, height = indicatorHeight)
                .then(
                    if (pageCount > 0) Modifier.background(
                        color = activeColor,
                        shape = indicatorShape,
                    )
                    else Modifier
                )
        )
    }
}

internal interface PagerStateBridge {
    val currentPage: Int
    val currentPageOffset: Float
}


fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
}