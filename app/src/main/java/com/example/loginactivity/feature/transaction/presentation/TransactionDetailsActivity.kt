package com.example.loginactivity.feature.transaction.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.GenericDetailRow
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.core.base.testdatas.SiteDetails2
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.dashboard.presentation.ui.DriverLocationActivity
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.pumpoperation.ui.theme.LoginActivityTheme

class TransactionDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
                { innerPadding ->
                    ShowTransactionDemo(innerPadding = innerPadding, intent)
//                    ShowTransactionDemo()

                }
            }
        }
        hideSystemUI()
    }

}

@Composable
fun ShowTransactionDemo(innerPadding: PaddingValues, intent: Intent) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        ShowTransaction(innerPadding = innerPadding, intent)
    }
}

@Composable
fun ShowTransaction(innerPadding: PaddingValues, intent: Intent) {
    val context = LocalContext.current
    val savedTransaction = intent.getParcelableExtra<TransactionDto>("savedTransaction")
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    DisposableEffect(backDispatcher) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        }

        backDispatcher?.addCallback(callback)

        onDispose {
            callback.remove()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Transaction", style = customTextStyle.displayLarge)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Successfull", style = customTextStyle.displayLarge)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(LocalConfiguration.current.screenHeightDp.dp * 0.3f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.success_icon),
                    contentDescription = "Your image description",
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.size(150.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GenericDetailRow("Site Name ", SiteDetails2.name)
                GenericDetailRow("Site Address ", SiteDetails2.address)
                savedTransaction?.let {

                    GenericDetailRow("Fuel Type ", it.fuelCode.orEmpty())
                    GenericDetailRow(
                        "Litres Fueled ",
                        "${it.quantity} Litres"
                    )
                    GenericDetailRow("Transaction Type ", it.transactionType.orEmpty())
                    GenericDetailRow("Vin Number ", it.vinNumber.toString())
                }


                ReusableElevatedButton(
                    onClick = {

                        context.startActivity(
                            Intent(
                                context, DriverLocationActivity::class.java
                            ).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                            }
                        )
                        (context as? Activity)?.finish()

                    },
                    text = "Start Next Transaction",
                    isEnabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun ShowTransaction2(innerPadding: PaddingValues) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Transaction", style = customTextStyle.displayLarge)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Successfull", style = customTextStyle.displayLarge)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(LocalConfiguration.current.screenHeightDp.dp * 0.3f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.success_icon),
                    contentDescription = "Your image description",
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.size(150.dp)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GenericDetailRow("Site Name ", SiteDetails2.name)
                GenericDetailRow("Site Address ", SiteDetails2.address)
                GenericDetailRow("Fuel Type ", SiteDetails2.tankSites[0].fuelType)
                GenericDetailRow(
                    "Litres Fueled ",
                    "${SiteDetails2.tankSites[0].tankCapacity} Litres"
                )

                ReusableElevatedButton(
                    onClick = {

                        context.startActivity(
                            Intent(
                                context, DriverLocationActivity::class.java
                            )
                        )
                    },
                    text = "Start Next Transaction",
                    isEnabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }


        }
    }
}

@Composable
fun Greeting6(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingPreview3() {
    LoginActivityTheme {
        Greeting6("Android")
    }
}