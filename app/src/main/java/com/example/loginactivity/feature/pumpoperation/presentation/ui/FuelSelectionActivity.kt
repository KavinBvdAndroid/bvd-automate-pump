package com.example.loginactivity.feature.pumpoperation.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.poppinsFontFamily
import com.example.loginactivity.feature.pumpoperation.data.model.InyardTanksItem
import com.example.loginactivity.core.base.testdatas.listOfInYardItems
import com.example.loginactivity.feature.pumpoperation.ui.theme.LoginActivityTheme

class FuelSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SelectFuel(innerPadding)
                }
            }
        }
    }
}

enum class Option {
    OPTION_1,
    OPTION_2
}

@Preview(showBackground = true)
@Composable
fun SelectFuelDemo() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        SelectFuel(innerPadding)
    }
}


@Composable
fun SelectFuel(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val offset = Offset(15.0f, 10.0f)

    var selectedOption by rememberSaveable { mutableStateOf<Option?>(null) }
    var showYardDetails by rememberSaveable { mutableStateOf<Boolean>(false) }

    Column(modifier = Modifier.padding(innerPadding)) {
        Text(
            "Select the transaction", modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
            RadioButton(
                selected = selectedOption == Option.OPTION_1,
                onClick = {
                    selectedOption = Option.OPTION_1
                    showYardDetails = true

                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Wheel to Bulk")
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == Option.OPTION_2,
                    onClick = {
                        selectedOption = Option.OPTION_2
                        showYardDetails = false
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Wheel to Wheel")
            }
        }


        Column {
            if (showYardDetails) {
                ShowYardDetails()
            }

        }
    }


}

@Composable
fun ShowYardDetails() {
    var newSelectedSite by rememberSaveable { mutableStateOf<InyardTanksItem?>(listOfInYardItems.first()) }
    val context = LocalContext.current
    var query by rememberSaveable { mutableStateOf("") }
    val filteredItems = listOfInYardItems.filter {
        it.productCode?.contains(query, ignoreCase = true) ?: false
    }
    val offset = Offset(15.0f, 10.0f)

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
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
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
                                Text(
                                    text = "Tank type : ${it.tankType}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = "Tank Description : ${it.tankDescription}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = "Tank Capacity : ${it.capacity}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = "Tank Safe Fill Limit : ${it.safeFillLimit}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Text(
                                    text = "Product Code : ${it.productCode}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    style = MaterialTheme.typography.titleLarge
                                )

                            }
                        }
                    }
                    Divider()
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(4.dp),
                color = Color.White
            ) {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Search") },
                    modifier = Modifier.fillMaxWidth()
                )

            }
            ReusableElevatedButton(
                onClick = {
                    context.startActivity(Intent(context, StartFuelingActivity::class.java))
                },
                text = "Continue",
                isEnabled = newSelectedSite != null,
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

