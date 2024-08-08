package com.example.loginactivity.core.base.generics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.loginactivity.R
import com.example.loginactivity.feature.automatefuel.data.model.PumpResponse

@Composable
fun GenericProgressBar(
    isLoading: Boolean,
    message: String = "Loading..."
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val screenHeight = configuration.screenHeightDp.dp

            val containerSize = minOf(screenWidth, screenHeight) * 0.5f
            val progressSize = containerSize * 0.4f
            val textSize = (containerSize * 0.08f)

            Column(
                modifier = Modifier
                    .size(containerSize)
                    .align(Alignment.Center)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(progressSize),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = (progressSize * 0.1f).value.dp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    style = customTextStyle.labelMedium
                )
            }
        }
    }
}

val arsenalFontFamily = FontFamily(
    Font(R.font.arsenal, FontWeight.Bold),
    Font(R.font.arsenal_bold, FontWeight.ExtraBold)
)

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold)
)


val customTextStyle = Typography(
    titleLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = Color.Black
    ),

    labelLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black
    ),
    headlineLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 26.sp,
        color = Color.Black
    ),
    displayLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp,
        color = Color.Black
    ),
    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )

)

val startMockPumpResponse = PumpResponse(
    msg = "Successfully started",
    params = "SuccessParams"
)

val stopMockPumpResponse = PumpResponse(
    msg = "Stopped successfully",
    params = "SuccessParams"
)


@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    switchWidth: Dp = 80.dp,
    switchHeight: Dp = 40.dp
) {
    Box(
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(CircleShape) // Ensures the switch maintains its rounded appearance
            .background(if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .size(switchWidth, switchHeight)
                .align(Alignment.Center),
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedThumbColor = MaterialTheme.colorScheme.onSecondary
            )
        )
    }
}




@Composable
fun ReusableTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    isError: Boolean = false,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    visualTransformation: VisualTransformation = if (isPassword) {
        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    },
    onPasswordVisibilityChange: (Boolean) -> Unit = {},
    trailingIcon: (@Composable () -> Unit)? = null,
    errorMessage: String = "",
    errorIndicatorColor: Color = colorResource(R.color.colorError),
    focusedIndicatorColor: Color = colorResource(R.color.colorPrimaryVariant),
    unfocusedIndicatorColor: Color = colorResource(R.color.black),
    textStyle: TextStyle = customTextStyle.labelLarge,
    keyboardType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onClick: (() -> Unit)? = null
) {

    Column(modifier = modifier) {

        OutlinedTextField(value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            keyboardOptions = keyboardType,
            textStyle = textStyle,
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = focusedIndicatorColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor,
                errorIndicatorColor = errorIndicatorColor
            ),
            maxLines = 1, // Restrict to one line
            singleLine = true,
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon ?: {
                if (isPassword) {
                    IconButton(onClick = { onPasswordVisibilityChange(!isPasswordVisible) }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (isError) 10.dp else 8.dp) // Adjust bottom padding if there's an error message
                .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
        )// Apply onClick if provided

        if (isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = errorIndicatorColor,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

    }

}


@Composable
fun ReusableElevatedButton(
    onClick: () -> Unit,
    text: String,
    isEnabled: Boolean = false,
    backgroundColor: Color = colorResource(R.color.colorPrimaryVariant),
    contentColor: Color = Color.White,
    textStyle: TextStyle = customTextStyle.labelLarge,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        ElevatedButton(
            onClick = onClick,
            enabled = isEnabled,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = backgroundColor,
                contentColor = contentColor,
                disabledContainerColor = colorResource(R.color.colorDisabled),
                disabledContentColor = colorResource(R.color.textDisabled)
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 16.dp
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            Text(
                text = text,
                style = textStyle
            )
        }
    }
}

@Preview
@Composable
fun LoginLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.6f)
            .clip(
                RoundedCornerShape(
                    bottomStart = 46.dp,
                    bottomEnd = 46.dp
                )
            )
            .background(colorResource(id = R.color.colorSecondary)),

        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bvd_truck_logo),
            contentDescription = "Your image description",
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ErrorAlertDialog(
    title: String,
    message: String,
    buttonText: String,
    onDismiss: () -> Unit,
    titleBackgroundColor: Color = MaterialTheme.colorScheme.errorContainer,
    titleCornerRadius: Int = 16 // Default rounded corner radius
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Box(
                modifier = Modifier
                    .background(
                        titleBackgroundColor,
                        shape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50)
                    )
                    .padding(16.dp)
                    .shadow(8.dp)
            ) {
                Text(
                    text = title,
                    style = customTextStyle.titleLarge
                )
            }
        },
        text = {
            Text(
                text = message,
                style = customTextStyle.labelMedium,
                modifier = Modifier.padding(16.dp)
            )
        },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = buttonText)
            }
        },
        properties = DialogProperties(dismissOnClickOutside = false)
    )
}


@Composable
fun GenericDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label: ",
            style = customTextStyle.labelLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = customTextStyle.labelLarge,
            modifier = Modifier.weight(1f)
        )
    }
}
//@Composable
//fun Dp.toSp(): TextUnit {
//    val density = LocalDensity.current.density
//    val pixels = this.toPx() // Convert Dp to pixels
//    return (pixels / density).sp // Convert pixels to sp
//}