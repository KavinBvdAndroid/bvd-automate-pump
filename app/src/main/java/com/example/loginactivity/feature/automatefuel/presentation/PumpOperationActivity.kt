package com.example.loginactivity.feature.automatefuel.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.feature.automatefuel.ui.theme.LoginActivityTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFuelingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StartFuel(innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartFuelingDemo() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        StartFuel(innerPadding)
    }

}

@Composable
fun StartFuel(innerPadding: PaddingValues) {
    var checked by rememberSaveable { mutableStateOf(false) }
    var isStartEnabled by rememberSaveable { mutableStateOf(true) }
    var isStopEnabled by rememberSaveable { mutableStateOf(false) }
    var processStatus by rememberSaveable { mutableIntStateOf(-1) }
    val context = LocalContext.current
    val offset = Offset(15.0f, 10.0f)

    val textColor by remember {
        derivedStateOf {
            when (processStatus) {
                0 -> Color.Red
                1 -> Color.Green
                else -> Color.LightGray // Default color if none of the conditions match
            }
        }
    }

    val processStatusText by remember {
        derivedStateOf {
            when (processStatus) {
                0 -> "Turned OFF"
                1 -> "Turned ON"
                else -> "Not Started" // Default color if none of the conditions match
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        HeaderSection()
        ActionButtons(isStartEnabled = isStartEnabled, isStopEnabled = isStopEnabled, onStartClick = {
            isStartEnabled = false
            processStatus = 1
            isStopEnabled = true
        }, onStopClick = {
            isStopEnabled = false
            processStatus = 0
            isStartEnabled = true
        })
        StatusTextSection(processStatusText, textColor)
        ResultSection()
        AgreementSection(checked = checked, { checked = it }, context)
    }

}

@Composable
fun AgreementSection(
    checked: Boolean, onCheckedChange: (Boolean) -> Unit, context: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)

        Text(
            style = customTextStyle.titleLarge,
            text = stringResource(id = R.string.i_fuel_activate_verification),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
    }

    ReusableElevatedButton(
        onClick = {
            AppUtils.showToastMessage("Validated...")
            context.startActivity(
                Intent(context, TransactionActivity::class.java)
            )
        },
        text = "Agree",
        isEnabled = checked,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun HeaderSection() {

    val offset = Offset(15.0f, 10.0f)
    Text(
        text = "Activate & Deactivate the Pump",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        color = colorResource(id = R.color.colorOnPrimary),
        style = TextStyle(
            fontSize = 28.sp,
            shadow = Shadow(
                color = Color.LightGray, offset = offset, blurRadius = 3f
            ),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle(R.font.poppins_extra_bold)
        )
    )


}

@Composable
fun ActionButtons(
    isStartEnabled: Boolean, isStopEnabled: Boolean,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ElevatedButton(
            onClick = {
                onStartClick()

            },
            shape = CircleShape,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = colorResource(id = R.color.colorOnPrimary),
                contentColor = Color.White,
                disabledContainerColor = colorResource(id = R.color.disabledStartButtonColor),
            ),
            enabled = isStartEnabled,
            modifier = Modifier
                .size(100.dp)
                .padding(0.dp),

            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 12.dp,
                pressedElevation = 16.dp// Adjust shadow elevation as needed
            ),

            ) {

            Text(text = " START ")
        }

        ElevatedButton(
            onClick = {
                onStopClick()
            },
            shape = CircleShape,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = colorResource(id = R.color.colorError),
                contentColor = Color.White,
                disabledContainerColor = colorResource(id = R.color.disabledStopButtonColor),
            ),
            enabled = isStopEnabled,
            modifier = Modifier
                .size(100.dp)
                .padding(0.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 16.dp// Adjust shadow elevation as needed
            )
        ) {
            Text(" STOP ", textAlign = TextAlign.Center, maxLines = 1)
        }
    }
}

@Composable
fun StatusTextSection(processStatusText: String, textColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Status : ",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal,
            style = customTextStyle.headlineLarge,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = processStatusText,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            style = customTextStyle.headlineLarge,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ResultSection() {
    Column {

        Text(
            text = "Result : ",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal,
            style = customTextStyle.headlineLarge,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 120.dp)
                .border(width = 1.dp, color = Color.Black)
                .background(color = Color.LightGray)
                .padding(8.dp)
        ) {
            Text(
                text = "Output : {result : Success, litres_fueled : 120 litres}",
                textAlign = TextAlign.Start,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }


    @Composable
    fun StartFuel1(innerPadding: PaddingValues) {

        var checked by rememberSaveable { mutableStateOf(false) }
        var isStartEnabled by rememberSaveable { mutableStateOf(true) }
        var isStopEnabled by rememberSaveable { mutableStateOf(false) }
        var processStatus by rememberSaveable { mutableIntStateOf(-1) }

        val textColor by remember {
            derivedStateOf {
                when (processStatus) {
                    0 -> Color.Red
                    1 -> Color.Green
                    else -> Color.LightGray // Default color if none of the conditions match
                }
            }
        }

        val processStatusText by remember {
            derivedStateOf {
                when (processStatus) {
                    0 -> "Fueling Stopped"
                    1 -> "Fueling in Process"
                    else -> "Not Started" // Default color if none of the conditions match
                }
            }
        }


        val coroutineScope = rememberCoroutineScope()

        val context = LocalContext.current

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Column(modifier = Modifier.heightIn(LocalConfiguration.current.screenHeightDp.dp * 0.3f)) {


                    Text(
                        text = "Activate and Deactivate the Pump",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        style = customTextStyle.headlineLarge
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        ElevatedButton(
                            onClick = {
                                isStartEnabled = false
                                processStatus = 1
                                coroutineScope.launch {
                                    delay(2000)
                                    isStopEnabled = true
                                }

                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = colorResource(id = R.color.colorOnPrimary),
                                contentColor = Color.White,
                                disabledContainerColor = colorResource(id = R.color.disabledStartButtonColor),
                            ),
                            enabled = isStartEnabled,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(0.dp),

                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 8.dp // Adjust shadow elevation as needed
                            ),

                            ) {

                            Text(text = " START ")
                        }

                        ElevatedButton(
                            onClick = {
                                AppUtils.showToastMessage("Fueling Stopped....")
                                isStopEnabled = false
                                processStatus = 0
                                coroutineScope.launch {
                                    delay(10000)
                                }
                                isStartEnabled = true
                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = colorResource(id = R.color.colorError),
                                contentColor = Color.White,
                                disabledContainerColor = colorResource(id = R.color.disabledStopButtonColor),
                            ),
                            enabled = isStopEnabled,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(0.dp),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 8.dp // Adjust shadow elevation as needed
                            )
                        ) {
                            Text(" STOP ", textAlign = TextAlign.Center, maxLines = 1)
                        }


                    }

                }
                Column(modifier = Modifier.background(Color.White)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Status : ",
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Normal,
                            style = customTextStyle.headlineLarge,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = processStatusText,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor,
                            style = customTextStyle.headlineLarge,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Text(
                        text = "Result : ",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        style = customTextStyle.headlineLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sizeIn(minHeight = 120.dp)
                            .border(width = 1.dp, color = Color.Black)
                            .background(color = Color.LightGray)
                            .padding(8.dp) // Optional padding for inner content
                    ) {
                        Text(
                            text = "Output : {result : Success, litres_fueled : 120 litres}",
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize()
                            .padding(end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = checked, onCheckedChange = { checked = it })

                        Text(
                            style = customTextStyle.titleLarge,
                            text = stringResource(id = R.string.i_fuel_activate_verification),
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                        )
                    }
                    ReusableElevatedButton(
                        onClick = {
                            AppUtils.showToastMessage("Validated...")
                            context.startActivity(
                                Intent(
                                    context,
                                    TransactionActivity::class.java
                                )
                            )
                        },
                        text = "Agree",
                        isEnabled = checked,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }


        }
    }


    @Composable
    fun Greeting5(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}
