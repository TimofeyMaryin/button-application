package custom.lib.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import custom.lib.button.ui.theme.ButtonTheme
import custom.lib.button_application.ButtonBrushBackground
import custom.lib.button_application.ButtonPrimary
import custom.lib.button_application.ButtonSecondary
import custom.lib.button_application.ButtonSecondaryCircle
import custom.lib.button_application.ButtonSecondaryIcon
import custom.lib.button_application.PosIcon
import custom.lib.button_application.background
import custom.lib.button_application.onBackground
import custom.lib.button_application.secondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            ButtonPrimary(value = "Button 1", background = background) {

                            }
                        }
                        item {
                            ButtonPrimary(
                                value = "Button 2",
                                background = onBackground,
                                contentColor = secondary
                            ) {

                            }
                        }

                        item {
                            ButtonSecondaryIcon(
                                value = "Button 1 Icon",
                                icon = Icons.Default.Check
                            ) {

                            }
                        }

                        item {
                            ButtonSecondaryIcon(
                                value = "Button 2 Icon",
                                icon = Icons.Default.Call,
                                posIcon = PosIcon.START
                            ) {

                            }
                        }

                        item {
                            ButtonSecondaryIcon(
                                value = "Button 3 Icon",
                                icon = Icons.Default.Call,
                                posIcon = PosIcon.END
                            ) {

                            }
                        }

                        item {
                            ButtonSecondary(value = "Button Secondary") {

                            }
                        }
                        item { ButtonSecondaryCircle(value = "Button Circle") {} }
                        
                        item { ButtonSecondaryCircle(value = "GradeGray", back = ButtonBrushBackground.GradeGray) {} }
                        item { ButtonSecondaryCircle(value = "JShine", back = ButtonBrushBackground.JShine) {} }
                        item { ButtonSecondaryCircle(value = "MoonlitAsteroid", back = ButtonBrushBackground.MoonlitAsteroid) {} }
                        item { ButtonSecondaryCircle(value = "Amin", back = ButtonBrushBackground.Amin) {} }
                        item { ButtonSecondaryCircle(value = "Wiretab", back = ButtonBrushBackground.Wiretab) {} }
                        item { ButtonSecondaryCircle(value = "CitrusPeel", back = ButtonBrushBackground.CitrusPeel) {} }
                        item { ButtonSecondaryCircle(value = "RainbowBlue", back = ButtonBrushBackground.RainbowBlue) {} }
                    }
                }
            }
        }
    }
}

