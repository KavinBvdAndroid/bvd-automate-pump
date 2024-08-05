package com.example.loginactivity.feature.auth.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.GenericDetailRow
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.ReusableTextInput
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.core.base.generics.isValidVinNumber
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.core.base.utils.Constants
import com.example.loginactivity.feature.auth.data.model.VehicleDetail
import com.example.loginactivity.feature.auth.ui.theme.LoginActivityTheme
import com.example.loginactivity.feature.automatefuel.presentation.FetchingSiteLocationCompose

class VinNumberActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VinContent(innerPadding)

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VinDemo() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        VinContent(innerPadding)
    }

}

@Composable
fun VinContent(innerPadding: PaddingValues) {
    var vinNumber by rememberSaveable { mutableStateOf("") }
    var isVinNumberValid by rememberSaveable { mutableStateOf(false) }
    var showVehicleDetails by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = LocalConfiguration.current.screenHeightDp.dp * 0.3f)
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                style = customTextStyle.titleLarge,
                text = stringResource(id = R.string.h_vin_number),
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            )

            ReusableTextInput(
                value = vinNumber,
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { newValue ->
                    if (newValue.length <= Constants.VIN_NUMBER_LENGTH) {
                        vinNumber = newValue
                    } else {
                        vinNumber = newValue.take(Constants.VIN_NUMBER_LENGTH)
                    }
                    isVinNumberValid =
                        newValue.isNotEmpty() && newValue.isNotBlank() && vinNumber.isValidVinNumber().second
                },
                label = stringResource(id = R.string.l_vin_number),
                isError = vinNumber.isNotBlank() && !isVinNumberValid,
                errorMessage = vinNumber.isValidVinNumber().first,
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            ReusableElevatedButton(
                onClick = {
                    AppUtils.showToastMessage("Validated...")
                    showVehicleDetails = true
                },
                text = "Submit",
                isEnabled = isVinNumberValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = LocalConfiguration.current.screenHeightDp.dp * 0.7f)
                .padding(start = 16.dp, end = 16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VehicleDetails(showVehicleDetails)

        }
    }


}

@Composable
fun VehicleDetails(showVehicleDetails: Boolean) {
    if (showVehicleDetails) {
        val context = LocalContext.current

        var checked by rememberSaveable { mutableStateOf(false) }
        AnimatedVisibility(
            visible = showVehicleDetails,
            enter = slideInHorizontally() + expandVertically(animationSpec = tween(durationMillis = 2000)),  // 1 second duration
            exit = fadeOut() + shrinkVertically(animationSpec = tween(durationMillis = 2000))
        ) {


            Column(

                modifier = Modifier
                    .animateContentSize()
                    .fillMaxSize()
                ,

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = customTextStyle.titleLarge,
                    text = stringResource(id = R.string.h_verify_vehicle_details),
                    modifier = Modifier.padding( bottom = 16.dp),
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GenericDetailRow(
                        label = "Vehicle Vin Number",
                        value = testVehicleDetails.vinNumber.orEmpty()
                    )
                    GenericDetailRow(
                        label = "Truck Name",
                        value = testVehicleDetails.name.orEmpty()
                    )
                    GenericDetailRow(label = "Color", value = testVehicleDetails.color.orEmpty())
                    GenericDetailRow(
                        label = "Driver Name",
                        value = testVehicleDetails.owner.orEmpty()
                    )
                    GenericDetailRow(
                        label = "Fuel type",
                        value = testVehicleDetails.fuelType.orEmpty()
                    )
                    GenericDetailRow(
                        label = "Vehicle Number",
                        value = testVehicleDetails.number.orEmpty()
                    )
                    GenericDetailRow(
                        label = "Vehicle Mileage",
                        value = testVehicleDetails.mileage.orEmpty()
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
                        style = customTextStyle.labelMedium,
                        text = stringResource(id = R.string.i_vin_number_message),
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    )
                }

                ReusableElevatedButton(
                    onClick = {
                        AppUtils.showToastMessage("Validated...")
                        context.startActivity(
                            Intent(
                                context,
                                FetchingSiteLocationCompose::class.java
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


@Preview(showBackground = true)
@Composable
fun Greeting2() {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + expandVertically(animationSpec = tween(durationMillis = 10000)),  // 1 second duration
        exit = fadeOut(),
        modifier = Modifier

            .padding(bottom = 16.dp),
    ) {
        Text(text = "Hello")
    }
}


val testVehicleDetails = VehicleDetail(
    name = "Volvo Truck",
    color = "White",
    model = "CX2558",
    owner = "John",
    number = "T2W 777",
    fuelType = "Diesel",
    vinNumber = "123456789",
    mileage = "300000 kms"

)

