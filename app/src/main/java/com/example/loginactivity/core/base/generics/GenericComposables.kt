package com.example.loginactivity.core.base.generics

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.lightColors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.maps.data.model.DataItem
import com.example.loginactivity.feature.maps.data.model.InyardSiteAccessDetails
import com.example.loginactivity.feature.maps.data.model.State
import com.example.loginactivity.feature.maps.domain.model.FuelSite

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

val Colors = lightColors(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC5),
    // Define other colors as needed
)

//@Composable
//fun myAppColorScheme(): ColorScheme {
//    return lightColorScheme(
//        primary = colorResource(id = R.color.colorPrimary),
//        onPrimary =  colorResource(id = R.color.colorOnPrimary),
//        primaryContainer = Color(0xFFF6AF2B),
//        onPrimaryContainer = Color(0xFF282F77),
//        secondary = Color(0xFF000740),
//        onSecondary = Color(0xFFFFFFFF),
//        secondaryContainer = Color(0xFF424242),
//        onSecondaryContainer = Color(0xFFFFFFFF),
//        background = Color.White,
//        onBackground = Color.Black,
//        surface = Color.White,
//        onSurface = Color.Black,
//        error = Color(0xFFA6003D),
//        onError = Color.White,
//        outline = Color(0xFF77A6003D)
//
//    )
//}
//
//@Composable
//fun MyAppTheme(content: @Composable () -> Unit) {
//    val colorScheme = myAppColorScheme()
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = customTextStyle,
//        content = content
//    )
//}


val customTextStyle = Typography(
    titleLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = Color.Black
    ),

    titleMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
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

val offset = Offset(15.0f, 10.0f)

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
    errorIndicatorColor: Color = MaterialTheme.colorScheme.error,
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
    val keyboardController = LocalSoftwareKeyboardController.current

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
            .background(colorResource(id = R.color.colorOnPrimary)),

        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.footer_logo),
            contentDescription = "Your image description",
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(420.dp)
        )
    }
}

@Composable
fun GenericShadowHeader(label: String, modifier: Modifier, textAlign: TextAlign = TextAlign.Center){
    Text(
        label, modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = textAlign,
        color = colorResource(id = R.color.colorSecondary),
        style = TextStyle(
            fontSize = 30.sp,
            shadow = Shadow(
                color = Color.LightGray, offset = offset, blurRadius = 3f
            ),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold
        )
    )
}
@Composable
fun ElevatedCircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(260.dp) // Size of the circle button
            .background(Color.Transparent), // Transparent background to show only the border
        shape = CircleShape, // Makes the button circular
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // Transparent background for the button
            contentColor = Color.Blue, // Text and icon color
            disabledContainerColor = Color.Gray, // Background color when disabled
            disabledContentColor = Color.DarkGray // Content color when disabled
        ),
        border = BorderStroke(2.dp, Color.Blue) // Outlined border color and thickness
    ) {
        content() // Button content (icon or text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTopBarWithBackButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    title:String=""
) {

    TopAppBar(
        title = {
            Text(text = title,
                modifier=Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 30.sp,
                    color = colorResource(id = R.color.colorSecondary),
                    textAlign = TextAlign.Center,
                    shadow = Shadow(
                        color = Color.LightGray, offset = offset, blurRadius = 3f)
            ))

        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, // Back arrow icon
                    contentDescription = "Back",
                    tint = colorResource(id = R.color.colorSecondary) // Set the tint to your desired color
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent, // Transparent background
            scrolledContainerColor = Color.LightGray
        ),
        modifier = modifier.fillMaxWidth(),
         scrollBehavior = scrollBehavior
    )
}

@Composable
fun ElevatedCircleButtonShadow(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.size(64.dp), // Size of the circle button
        shape = CircleShape, // Makes the surface circular
        color = Color.Transparent, // Background color of the button
        shadowElevation = 8.dp, // Elevation for the shadow effect
        border = BorderStroke(2.dp, Color.Blue) // Border color and thickness
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
            shape = CircleShape, // Ensures the button is circular
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // Transparent background for the button
                contentColor = Color.Blue // Text and icon color
            ),
        ) {
            content() // Button content (icon or text)
        }
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
    var showDialog by rememberSaveable { mutableStateOf(true) }
    if (showDialog) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
                onDismiss()
            },
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
                Button(onClick = {
                    showDialog = false
                    onDismiss()
                }) {
                    Text(text = buttonText)
                }
            },
            properties = DialogProperties(dismissOnClickOutside = false)
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
            text = "$label : ",
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

fun transferToFuelSites(dtoList: List<DataItem?>): List<FuelSite> {
    return dtoList.map { dto ->
        FuelSite(
            longitudeDirection = dto?.longitudeDirection ?: "N/A",
            inyardTanks = dto?.inyardTanks?.filterNotNull() ?: emptyList(),
            inYard = dto?.inYard ?: 0,
            city = dto?.city ?: "N/A",
            latitude = dto?.latitude ?: 0.0,
            createdAt = dto?.createdAt ?: "N/A",
            latitudeDirection = dto?.latitudeDirection ?: "N/A",
            number = dto?.number ?: "N/A",
            updatedAt = dto?.updatedAt ?: "N/A",
            timings = dto?.timings?.filterNotNull() ?: emptyList(),
            id = dto?.id ?: 0,
            stateId = dto?.stateId ?: 0,
            state = dto?.state ?: State(), // Ensure State class has a default constructor
            longitude = dto?.longitude ?: 0.0,
            zip = dto?.zip ?: "N/A",
            inyardSiteAccessDetails = dto?.inyardSiteAccessDetails ?: InyardSiteAccessDetails(), // Ensure this class has a default constructor
            hours = dto?.hours ?: "N/A",
            address = dto?.address ?: "N/A",
            inyardSiteType = dto?.inyardSiteType ?: "N/A",
            deleted = dto?.deleted ?: 0,
            groupNumber = dto?.groupNumber ?: "N/A",
            phone = dto?.phone ?: "N/A",
            name = dto?.name ?: "N/A",
            manned = dto?.manned ?: 0,
            countryId = dto?.countryId ?: 0,
            contacts = dto?.contacts?.filterNotNull() ?: emptyList(),
            status = dto?.status ?: 0
        )
    }
}


fun formatLatitude( latitude:Double, latitudeDirection:String) : Double{
    val formattedLatitude =  if (latitudeDirection == "S") {
        "-${"%.4f".format(latitude)}"
    } else {
        "+${"%.4f".format(latitude)}"
    }
    return formattedLatitude.toDouble()
}
fun formatLongitude( latitude:Double, longitudeDirection:String) : Double{
    val formattedLongitude =  if (longitudeDirection == "W") {
        "-${"%.4f".format(latitude)}"
    } else {
        "+${"%.4f".format(latitude)}"
    }
    return formattedLongitude.toDouble()
}