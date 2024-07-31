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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginactivity.R

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
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = (14.sp),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

val customFontFamily = FontFamily(
    Font(R.font.arsenal, FontWeight.Bold),
    Font(R.font.arsenal_bold, FontWeight.ExtraBold)
)

val customTextStyle = Typography(
    titleLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.Black
    ),

    labelLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black
    )
)

@Composable
fun ReusableTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    isError: Boolean,
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
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.4f)
            .clip(
                RoundedCornerShape(
                    bottomStart = 26.dp,
                    bottomEnd = 26.dp
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
fun GenericDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
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