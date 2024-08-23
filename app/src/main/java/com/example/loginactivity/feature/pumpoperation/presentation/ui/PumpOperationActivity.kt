package com.example.loginactivity.feature.pumpoperation.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.ErrorAlertDialog
import com.example.loginactivity.core.base.generics.GenericProgressBar
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.TransparentTopBarWithBackButton
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.core.base.testdatas.requestTransaction
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.maps.data.model.DataItem
import com.example.loginactivity.feature.pumpoperation.data.model.PumpParams
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionState
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.pumpoperation.presentation.viewmodel.PumpOperationViewModel
import com.example.loginactivity.feature.pumpoperation.ui.theme.LoginActivityTheme
import com.example.loginactivity.feature.transaction.presentation.TransactionDetailsActivity
import com.example.loginactivity.feature.transaction.presentation.ui.theme.Blue700
import com.example.loginactivity.ui.theme.ColorSecondary
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartFuelingActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val selected_yard = intent?.getParcelableExtra<DataItem>("selected_yard")
        setContent {
            LoginActivityTheme {
                StartFuelingDemo(selected_yard)
            }
        }
        hideSystemUI()
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartFuelingDemo(selected_yard: DataItem?) {
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var isBackButtonEnabled by rememberSaveable { mutableStateOf(true) }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    DisposableEffect(backDispatcher) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isBackButtonEnabled) {
                    val intent = Intent(context, FuelSelectionActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    context.startActivity(intent)
                }
            }
        }

        backDispatcher?.addCallback(callback)

        onDispose {
            callback.remove()
        }
    }


    val topBarColor by remember {
        derivedStateOf {
            // Change color based on scroll position
            if (scrollState.firstVisibleItemScrollOffset > 100) {
                Blue700 // Replace with your desired color
            } else {
                Color.Transparent // Initial color when at the top
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            TransparentTopBarWithBackButton(
                onBackClick = {
                    if (isBackButtonEnabled) {
                        val intent = Intent(context, FuelSelectionActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        context.startActivity(intent)
                    }

                },
                scrollBehavior = scrollBehavior,
                topBarColor = topBarColor
            )
        }
    ) { innerPadding ->
        selected_yard?.let {
            StartFuel(innerPadding, {
                isBackButtonEnabled = false
            }, it)
        }
    }

}



@Composable
fun StartFuel(innerPadding: PaddingValues, onBackButtonDisabled: () -> Unit,selected_yard:DataItem) {
    val viewModel: PumpOperationViewModel = hiltViewModel()
    val pumpStartLivedata by viewModel.pumpStartLivedata.observeAsState(null)
    val pumpStopLivedata by viewModel.pumpStopLivedata.observeAsState(null)
    val saveTransactionLivedata by viewModel.saveTransactionLivedata.collectAsState(null)
    var checked by rememberSaveable { mutableStateOf(false) }
    var isStartEnabled by rememberSaveable { mutableStateOf(false) }
    var isTransactionComplete by rememberSaveable { mutableStateOf(false) }



//  var isStopEnabled by rememberSaveable { mutableStateOf(false) }
    var processStatus by rememberSaveable { mutableIntStateOf(-1) }
    var result by rememberSaveable {
        mutableStateOf("Note: Result will appear here at the end of the every Transaction")
    }
    val context = LocalContext.current
//    val offset = Offset(15.0f, 10.0f)

    val testParamsData = PumpParams()


    val textColor = if (isStartEnabled) {
        colorResource(id = R.color.colorOnText1)
    } else {
        Color.Red
    }


    val processStatusText by remember {
        derivedStateOf {
            if (isStartEnabled) {
                "PUMP Activated"

            } else {
                "PUMP De-Activated"
            }
        }
    }


    val handleSaveTransaction:(TransactionDto) -> Unit = {transactionDto->
        viewModel.saveTransaction(transactionDto)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp * 0.1f))
            SwitchButton(isPumpEnabled = isStartEnabled, onSwitchClick = {
                if (it) {
                    viewModel.startPump("PMPON", testParamsData.toString())
                } else {
                    viewModel.stopPump("PMPOFF", testParamsData.toString())
                }
            })
            Spacer(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp * 0.1f))

            StatusTextSection(processStatusText, textColor)
            Spacer(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp * 0.1f))
            ResultSection(result)

            AgreementSection(
                isTransactionComplete,
                checked = checked,
                { checked = it },
                handleSaveTransaction,
                context
            )
        }

    }


    pumpStartLivedata?.let {
        GenericProgressBar(isLoading = false)
        ObservePumpData(pumpResponseData = it) { code, data, message ->
            if (code == 1) {
                isStartEnabled = true
                result = data?.msg.toString()
                isTransactionComplete = false
                checked = false
                onBackButtonDisabled()
            } else {
                isStartEnabled = false
                if (message != null) {
                    result = message
                }
            }

        }
    }

    pumpStopLivedata?.let {
        GenericProgressBar(isLoading = false)
        ObservePumpData(pumpResponseData = it) { code, data, message ->
            if (code == 1) {
                isStartEnabled = false
                isTransactionComplete = true
                result = data?.msg.toString()
                handleSaveTransaction
            } else {
                isStartEnabled = true
                if (message != null) {
                    result = message
                }
            }

        }
    }



    when (val state = saveTransactionLivedata) {
        is TransactionState.Idle -> {

        }

        is TransactionState.Loading -> {
            GenericProgressBar(isLoading = true)
        }

        is TransactionState.Success -> {
            context.startActivity(
                Intent(
                    context,
                    TransactionDetailsActivity::class.java
                ).putExtra("savedTransaction", state.saveTransactionDto)
            )
        }

        is TransactionState.PartialSuccess -> {
            ErrorAlertDialog(title = "Error",
                message = state.message,
                buttonText = "Ok",
                titleBackgroundColor = Color.Red,
                onDismiss = {
                    context.startActivity(
                        Intent(
                            context,
                            TransactionDetailsActivity::class.java
                        ).putExtra("savedTransaction", state.saveTransactionDto)
                    )
                })
        }

        is TransactionState.Error -> {
            ErrorAlertDialog(title = "Error",
                message = state.message,
                buttonText = "Ok",
                titleBackgroundColor = Color.Red,
                onDismiss = { })
        }

        null -> {

        }
    }
}

@Composable
fun ObservePumpData(
    pumpResponseData: Resource<PumpResponse>,
    pumpResponseCallback: (Int, PumpResponse?, String?) -> Unit
) {
    when (pumpResponseData) {
        is Resource.Loading -> {
            GenericProgressBar(isLoading = true)
            Log.d("PumpOperationActivity Obsereving ", "Loading")
        }

        is Resource.Success -> {
            pumpResponseData.data?.msg
            Log.d("PumpOperationActivity Obsereving ", "Success")
            pumpResponseCallback(1, pumpResponseData.data, "")
        }

        is Resource.Failure -> {
            GenericProgressBar(isLoading = false)
            Log.d("PumpOperationActivity Obsereving ", "Failure")
            pumpResponseCallback(0, null, pumpResponseData.message)

        }

        else -> {}
    }
}

@Composable
fun AgreementSection(
    isTransactionComplete: Boolean,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    handleSaveTransaction:(TransactionDto) -> Unit,
    context: Context
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = ColorSecondary,
                    checkmarkColor = Color.White

                ),
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = isTransactionComplete
            )

            Text(
                style = customTextStyle.labelMedium,
                text = stringResource(id = R.string.i_fuel_activate_verification),
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp * 0.05f))
        ReusableElevatedButton(
            onClick = {
                handleSaveTransaction(requestTransaction)
            },
            text = "Submit Transaction",
            isEnabled = checked && isTransactionComplete,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SaveTransactionRemote() {

}

@Composable
fun HeaderSection() {
    val offset = Offset(15.0f, 10.0f)
    Text(
        text = "Activate & Deactivate the Pump",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        color = colorResource(id = R.color.colorOnPrimary),
        style = TextStyle(
            fontSize = 28.sp, shadow = Shadow(
                color = Color.LightGray, offset = offset, blurRadius = 3f
            ), fontWeight = FontWeight.Bold, fontStyle = FontStyle(R.font.poppins_extra_bold)
        )
    )


}

@Composable
fun SwitchButton(isPumpEnabled: Boolean, onSwitchClick: (Boolean) -> Unit) {
    val shadowColor = if (isPumpEnabled) Color.Green else Color.Gray
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .scale(2f, 2f)
            .shadow(elevation = 16.dp, shape = CircleShape, spotColor = shadowColor)
        // Adjust the elevation and shape as needed
    ) {
        Switch(modifier = Modifier
            .height(80.dp)
            .width(80.dp)
            .background(Color.Transparent),
            checked = isPumpEnabled,

            onCheckedChange = {
                onSwitchClick(it)
            },
            thumbContent = if (isPumpEnabled) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                {
                    Icon(
                        imageVector = Icons.Filled.RemoveCircleOutline,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }

            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onSecondary,
                checkedTrackColor = colorResource(id = R.color.colorOnText1),
                uncheckedThumbColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                uncheckedTrackColor = MaterialTheme.colorScheme.outline,
            )

        )
    }


}

@Composable
fun ActionButtons(
    isStartEnabled: Boolean,
    isStopEnabled: Boolean,
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
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Status  ",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            style = customTextStyle.headlineLarge,
            modifier = Modifier
        )

        Text(
            text = processStatusText,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            textAlign = TextAlign.Center,
            style = customTextStyle.headlineLarge,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ResultSection(result: String) {

    Text(
        text = "Result ",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Normal,
        style = customTextStyle.headlineLarge,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(8.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 180.dp)
            .border(width = 1.dp, color = Color.Black)
            .background(color = Color.LightGray)
            .padding(8.dp)
    ) {
        Text(
//            text = "Output : {result : Success, litres_fueled : 120 litres}",
            text = "{$result}",
            textAlign = TextAlign.Start,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

    }


    @Composable
    fun StartFuel1(innerPadding: PaddingValues) {

        var checked by rememberSaveable { mutableStateOf(false) }
        var isStartEnabled by rememberSaveable { mutableStateOf(true) }
        var isStopEnabled by rememberSaveable { mutableStateOf(false) }
        var processStatus by rememberSaveable { mutableIntStateOf(0) }

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
                            context.startActivity(
                                Intent(
                                    context, TransactionDetailsActivity::class.java
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
            text = "Hello $name!", modifier = modifier
        )
    }
}
