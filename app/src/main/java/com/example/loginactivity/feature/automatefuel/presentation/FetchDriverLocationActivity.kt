package com.example.loginactivity.feature.automatefuel.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.feature.ui.theme.LoginActivityTheme

class FetchingSiteLocationCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FetchLocationContent(innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentDemo() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        FetchLocationContent(innerPadding)
    }

}

@Composable
fun FetchLocationContent(innerPadding: PaddingValues) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = customTextStyle.titleLarge,
            text = stringResource(id = R.string.h_driver_location),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        )
        Text(
            style = customTextStyle.titleLarge,
            text = "2345 steels avenue, Brampton, ON",
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        )

        ElevatedButton(
            onClick = {
                context.startActivity(Intent(context, SiteLocationListActivityCompose::class.java))
            },
            modifier = Modifier
                .size(260.dp) // Adjust size as needed
                .background(Color.Blue, shape = CircleShape), // Background color and shape
            shape = CircleShape, // Button shape

            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), // Button colors
            contentPadding = PaddingValues(0.dp) // Remove padding to make it perfectly circular
        ) {
            Text(
                text = "Start fetching site locations",
                color = Color.White, // Text color
                modifier = Modifier.padding(10.dp) // Adjust padding for text
            )
        }
    }
}


@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginActivityTheme {
        Greeting3("Android")
    }
}