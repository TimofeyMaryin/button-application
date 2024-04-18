package custom.lib.button_application

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import custom.lib.text_application.PrimaryText
import custom.lib.text_application.SecondaryText
import kotlinx.coroutines.delay


val primary = Color(0xffFFDF00)
val secondary = Color(0xff0096FF)
val container = Color(0xff161616)
val background = Color(0xff222428)

val onContainer = Color(0xffe9e9e9)
val onBackground = Color(0xffdddbd7)

@Composable fun primaryClip() = MaterialTheme.shapes.medium
@Composable fun circleClip() = CircleShape

const val primarySizeFloat = 0.9f
const val primaryWidth = 0.9f
const val primaryHeight = 0.9f

val defaultIconSize = 20.dp

@Composable
fun ButtonPrimary(
    value: String,
    background: Color,
    contentColor: Color = background.invertColor(),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    var isClickable by remember{ mutableStateOf(false) }
    val scaleObject by animateFloatAsState(targetValue = if (isClickable) 1.2f else 1.0f, label = "")

    LaunchedEffect(
        key1 = isClickable,
        block = {
            if (isClickable) {
                delay(100)
                isClickable = false
            }
        },
    )

    Box(modifier = Modifier.padding(vertical = 15.dp)) {

        Box(
            modifier = Modifier
                .clip(primaryClip())
                .fillMaxWidth(primaryWidth)
                .scale(scaleObject)
                .background(background)
                .clickable {
                    isClickable = true
                    onClick()
                }
                .then(modifier),
            contentAlignment = Alignment.Center
        ) {
            PrimaryText(
                value = value.uppercase(),
                color = contentColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 15.dp)
            )
        }
    }
}


@Composable
fun ButtonSecondaryIcon(
    value: String,
    icon: ImageVector,
    posIcon: PosIcon = PosIcon.START,
    isActive: Boolean = false,
    onClick: () -> Unit,
) {

    val bg by animateColorAsState(targetValue = if (isActive) onBackground else background, label = "")
    val contentColor by animateColorAsState(targetValue = if (isActive) background else onBackground, label = "")


    Box(modifier = Modifier.padding(vertical = 15.dp)) {

        Box(
            modifier = Modifier
                .clip(primaryClip())
                .background(bg)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(
                    start = if (posIcon == PosIcon.START) 25.dp else 50.dp,
                    end = if (posIcon == PosIcon.START) 50.dp else 25.dp,
                    bottom = 15.dp,
                    top = 15.dp
                )
            ) {
                if (posIcon == PosIcon.START) {
                    Icon(
                        imageVector = icon, contentDescription = null,
                        modifier = Modifier.size(
                            defaultIconSize,
                        ),
                        tint = contentColor
                    )
                    Spacer(Modifier.width(20.dp))
                }
                SecondaryText(value = value, color = contentColor, fontWeight = FontWeight.SemiBold)
                if (posIcon == PosIcon.END) {
                    Spacer(Modifier.width(20.dp))
                    Icon(
                        imageVector = icon, contentDescription = null,
                        modifier = Modifier.size(
                            defaultIconSize,
                        ),
                        tint = contentColor
                    )
                }
            }
        }
    }

}

@Composable
fun ButtonSecondary(
    value: String,
    isActive: Boolean = false,
    onClick: () -> Unit,
) {

    val bg by animateColorAsState(targetValue = if (isActive) onBackground else background, label = "")
    val contentColor by animateColorAsState(targetValue = if (isActive) background else onBackground, label = "")


    Box(modifier = Modifier.padding(vertical = 15.dp)) {

        Box(
            modifier = Modifier
                .clip(primaryClip())
                .background(bg)
                .padding(vertical = 15.dp, horizontal = 50.dp)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            SecondaryText(value = value, color = contentColor, fontWeight = FontWeight.SemiBold)

        }
    }

}


@Composable
fun ButtonSecondaryCircle(
    value: String,
    isActive: Boolean = false,
    back: ButtonBrushBackground? = null,
    onClick: () -> Unit,
) {
    val conf = LocalConfiguration.current

    var bg: Brush? = null
    var contentColor by remember { mutableStateOf<Color?>(null) }

    if (back == null) {
        bg = Brush.horizontalGradient(if (isActive) listOf(onBackground, onBackground) else listOf(background, onBackground))
        contentColor = if (isActive) background else onBackground
    } else {
        contentColor = Color.White
        bg = Brush.horizontalGradient(
            when (back) {
                ButtonBrushBackground.GradeGray -> listOf(Color(0xffbdc3c7), Color(0xffbdc3c7))
                ButtonBrushBackground.JShine -> listOf(Color(0xff12c2e9), Color(0xffc471ed), Color(0xfff64f59))
                ButtonBrushBackground.MoonlitAsteroid -> listOf(Color(0xff0F2027), Color(0xff203A43), Color(0xfff64f59))
                ButtonBrushBackground.Amin -> listOf(Color(0xff8E2DE2), Color(0xff4A00E0))
                ButtonBrushBackground.Wiretab -> listOf(Color(0xff8A2387), Color(0xffE94057), Color(0xffF27121))
                ButtonBrushBackground.CitrusPeel -> listOf(Color(0xffFDC830), Color(0xffF37335))
                ButtonBrushBackground.RainbowBlue -> listOf(Color(0xff00F260), Color(0xff0575E6))
            }
        )

    }


    Box(modifier = Modifier.padding(vertical = 15.dp)) {

        Box(
            modifier = Modifier
                .clip(circleClip())
                .background(bg)
                .width((conf.screenWidthDp * 0.5).dp)
                .clickable {
                    onClick()
                    Log.e("TAG", "ButtonSecondaryCircle: ", )
                },
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(.3f)))
            SecondaryText(
                value = value,
                color = contentColor!!,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 15.dp)
            )

        }
    }
}

enum class ButtonBrushBackground {
    GradeGray, JShine, MoonlitAsteroid, Amin,
    Wiretab, CitrusPeel, RainbowBlue
}


@Composable
fun CircleNormalButton(
    value: String,
    background: Color,
    contentColor: Color = background.invertColor(),
    isBorder: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(circleClip())
            .background(background)
            .border(
                width = if (isBorder) 2.dp else 0.dp,
                color = if (isBorder) contentColor else Color.Transparent,
                shape = circleClip()
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        SecondaryText(
            value = value,
            color = contentColor,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
        )
    }
}


enum class PosIcon {
    START, END
}



fun Color.invertColor(): Color {
    val invertedRed = 1.0f - this.red
    val invertedGreen = 1.0f - this.green
    val invertedBlue = 1.0f - this.blue
    return Color(invertedRed, invertedGreen, invertedBlue, this.alpha)
}

@Preview
@Composable
fun Button_Preview() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ButtonPrimary(
            value = "Button",
            background = primary
        ) {

        }

        Spacer(modifier = Modifier.height(150.dp))
        ButtonSecondaryIcon(value = "Button", icon = Icons.Default.Send) {

        }
        Spacer(modifier = Modifier.height(30.dp))
        ButtonSecondaryIcon(
            value = "Button",
            icon = Icons.Default.Send,
            isActive = true
        ) {

        }
        Spacer(modifier = Modifier.height(30.dp))
        ButtonSecondaryIcon(
            value = "Button",
            icon = Icons.Default.Send,
            posIcon = PosIcon.END,
            isActive = true
        ) {

        }
        Spacer(modifier = Modifier.height(130.dp))
        CircleNormalButton(
            value = "Button",
            background = background
        ) {

        }
        Spacer(modifier = Modifier.height(30.dp))
        CircleNormalButton(
            value = "Button",
            background = onBackground,
            isBorder = true
        ) {

        }
    }

}