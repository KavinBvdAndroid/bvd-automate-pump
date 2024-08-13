package com.example.loginactivity.feature.pumpoperation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.core.base.testdatas.listOfInYardItems
import com.example.loginactivity.feature.pumpoperation.ui.theme.LoginActivityTheme

class TestComposableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting7(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting7(name: String, modifier: Modifier = Modifier) {

    LazyColumn (contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(listOfInYardItems){ item ->
            Card {
                Column {
                    Text(
                        text = "Hello ${item.productCode}!",
                        modifier = modifier
                    )
                }
            }

        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview4() {
    LoginActivityTheme {
        Greeting7("Android")
    }
}