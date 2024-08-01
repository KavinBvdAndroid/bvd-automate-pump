package com.example.loginactivity.feature.automatefuel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.feature.auth.VinNumberActivityCompose
import com.example.loginactivity.feature.automatefuel.data.model.InyardTanksItem
import com.example.loginactivity.feature.automatefuel.data.model.listOfInYardItems
import com.example.loginactivity.feature.automatefuel.ui.theme.LoginActivityTheme

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

    var selectedOption by rememberSaveable { mutableStateOf<Option?>(null) }
    var showYardDetails by rememberSaveable { mutableStateOf<Boolean>(false) }

    Column(modifier = Modifier.padding(innerPadding)) {
        Text("Select the transaction", style = MaterialTheme.typography.titleLarge)

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
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
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
        Column {
            if (showYardDetails){
                ShowYardDetails()
            }
        }
    }



}

@Composable
fun ShowYardDetails() {
    var newSelectedSite by rememberSaveable { mutableStateOf<InyardTanksItem?>(listOfInYardItems.first()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Select your Yard to proceed", style = MaterialTheme.typography.titleLarge)

        listOfInYardItems.forEach {
        Card(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
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

        ReusableElevatedButton(
            onClick = {
                AppUtils.showToastMessage("Validated...")
                context.startActivity(Intent(context, StartFuelingActivity::class.java))
            },
            text = "Continue",
            isEnabled = newSelectedSite!=null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

    }

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}}
