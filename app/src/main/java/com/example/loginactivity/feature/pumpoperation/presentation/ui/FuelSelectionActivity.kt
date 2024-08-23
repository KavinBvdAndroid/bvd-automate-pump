package com.example.loginactivity.feature.pumpoperation.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.GenericDetailRow
import com.example.loginactivity.core.base.generics.GenericShadowHeader
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.RoundSearchBox
import com.example.loginactivity.core.base.generics.TransparentTopBarWithBackButton
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.core.base.generics.poppinsFontFamily
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.maps.data.model.DataItem
import com.example.loginactivity.feature.pumpoperation.presentation.viewmodel.FuelSelectionViewModel
import com.example.loginactivity.feature.pumpoperation.ui.theme.LoginActivityTheme
import com.example.loginactivity.feature.transaction.presentation.ui.theme.Blue50

class FuelSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dataItem = intent?.getParcelableExtra<DataItem>("selectedSite")

        setContent {
            LoginActivityTheme {
                SelectFuelDemo(dataItem)
            }
        }
        hideSystemUI()
    }
}

enum class Option {
    OPTION_1,
    OPTION_2
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectFuelDemo(dataItem: DataItem?) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            TransparentTopBarWithBackButton(
                onBackClick = { backDispatcher?.onBackPressed() },
                scrollBehavior = scrollBehavior,
                topBarColor = Color.Transparent
            )
        }
    ) { innerPadding ->
        SelectFuel(innerPadding, dataItem)
    }
}


@Composable
fun SelectFuel(innerPadding: PaddingValues, dataItem: DataItem?) {
    val context = LocalContext.current
    val offset = Offset(15.0f, 10.0f)

    var selectedOption by rememberSaveable { mutableStateOf<Option?>(null) }
    var showYardDetails by rememberSaveable { mutableStateOf<Boolean>(false) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.Transparent)
    ) {
        Text(
            "Select the transaction", modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
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

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Row(
                modifier = Modifier
                    .clickable {
                        selectedOption = Option.OPTION_1
                        showYardDetails = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = selectedOption == Option.OPTION_1,
                    onClick = {
                        selectedOption = Option.OPTION_1
                        showYardDetails = true

                    }
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text("Wheel to Bulk", style = customTextStyle.titleMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedOption = Option.OPTION_2
                        showYardDetails = false
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedOption == Option.OPTION_2,
                    onClick = {
                        selectedOption = Option.OPTION_2
                        showYardDetails = false
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Wheel to Wheel", style = customTextStyle.titleMedium)
            }
        }


        Column(modifier = Modifier.background(Blue50)) {
            if (showYardDetails) {
                if (dataItem != null) {
                    var a = dataItem.inyardTanks
                    if (dataItem.inyardTanks.isNotEmpty()) {
                        ShowYardDetails(dataItem)
                    } else (
                            GenericShadowHeader(
                                label = ("No tanks found"),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            )
                }
            }

        }
    }
}

@Composable
fun ShowYardDetails(dataItem: DataItem) {
    val listOfInYardItems by rememberSaveable { mutableStateOf(dataItem.inyardTanks) }
    var newSelectedSite by rememberSaveable { mutableStateOf(listOfInYardItems.first()) }
    val context = LocalContext.current
    var query by rememberSaveable { mutableStateOf("") }
    val filteredItems = listOfInYardItems.filter {
        it.productCode?.contains(query, ignoreCase = true) ?: false
    }
    val offset = Offset(15.0f, 10.0f)

    val viewmodel :FuelSelectionViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Text(
            "Select your Yard to proceed", modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.colorSecondary),
            fontFamily = poppinsFontFamily,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.LightGray, offset = offset, blurRadius = 3f
                ),
                fontWeight = FontWeight.Bold
            )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        )
        {
//
//            Column(
//                modifier = Modifier
//                    .verticalScroll(rememberScrollState())
//                    .padding(12.dp, bottom = 66.dp)
//            ) {

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 80.dp),
                modifier = Modifier.wrapContentSize()
            ) {

                items(
                    items = filteredItems,
                    key = { item -> item.id!! }
                ) {

//                }
//                listOfInYardItems.forEach {
                    Card(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            newSelectedSite = it
                        },
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            RadioButton(
                                modifier = Modifier.size(24.dp),
                                selected = it.id == newSelectedSite?.id,
                                onClick = {
                                    newSelectedSite = it
                                })

                            Spacer(modifier = Modifier.width(8.dp))
                            Column {

                                GenericDetailRow(
                                    label = "Tank type",
                                    value = it.tankType.toString()
                                )
                                GenericDetailRow(
                                    label = "Description",
                                    value = it.tankDescription.toString()
                                )
                                GenericDetailRow(label = "Capacity", value = it.capacity.toString())
                                GenericDetailRow(
                                    label = "Safe limit",
                                    value = it.safeFillLimit.toString()
                                )
                                GenericDetailRow(
                                    label = "Product code",
                                    value = it.productCode.toString()
                                )

                            }
                        }
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopCenter),
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(50.dp),
            ) {
//                TextField(
//                    value = query,
//                    onValueChange = { query = it },
//                    label = { Text("Search") },
//                singleLine = true,
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Text,
//                        imeAction = ImeAction.Done
//                    ),
//                    maxLines = 1,
//                    modifier = Modifier.fillMaxWidth()
//                )

                RoundSearchBox(
                    value = query,
                    onValueChange = { query = it },
                )

            }
            ReusableElevatedButton(
                onClick = {
//                    viewmodel.storeTransactionDetails(newSelectedSite)
                    context.startActivity(
                        Intent(context, StartFuelingActivity::class.java).putExtra(
                            "selected_yard",
                            newSelectedSite
                        )
                    )
                },
                text = "Continue",
                isEnabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .align(Alignment.BottomCenter),
                )

        }

//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .background(color = Color.Transparent)){
//            ReusableElevatedButton(
//                onClick = {
//                    AppUtils.showToastMessage("Validated...")
//                    context.startActivity(Intent(context, StartFuelingActivity::class.java))
//                },
//                text = "Continue",
//                isEnabled = newSelectedSite != null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(0.dp)
//
//            )
//        }

    }
}

