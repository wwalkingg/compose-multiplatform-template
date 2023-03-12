import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Course(modifier: Modifier = Modifier, course: Course, onClick: (id: Int) -> Unit) {
    Row(modifier.height(60.dp).fillMaxWidth().clip(MaterialTheme.shapes.small).clickable { onClick(course.id) }) {
        Box(
            modifier = Modifier.width(114.dp).clip(MaterialTheme.shapes.small).fillMaxHeight().background(Color.Gray)
        ) {
            Text(
                difficultyText[course.difficulty],
                color = difficultyColor[course.difficulty],
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(difficultyBackgroundColor[course.difficulty])
                    .clip(RoundedCornerShape(0).copy(bottomEnd = MaterialTheme.shapes.small.bottomEnd))
                    .padding(4.dp, 2.dp)
            )
        }
        Spacer(Modifier.width(6.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(course.title, style = MaterialTheme.typography.titleMedium)
            Text(
                "${course.consumeEnergy}千卡·${course.consumeTime}·需要经验",
                style = MaterialTheme.typography.labelSmall
            )
            Text("${course.experienceRequirement}", style = MaterialTheme.typography.labelSmall)
        }
    }
}

val difficultyText = listOf("超简单", "简单", "中等", "难", "很难")
val difficultyColor = listOf(Color.Green, Color.Blue, Color.Yellow, Color.Red, Color.White)
val difficultyBackgroundColor = listOf(
    Color.Green.copy(.3f),
    Color.Blue.copy(.3f),
    Color.Yellow.copy(.3f),
    Color.Red.copy(.3f),
    Color.Gray
)