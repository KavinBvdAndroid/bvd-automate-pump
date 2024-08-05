package com.example.loginactivity.feature.automatefuel.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.loginactivity.feature.automatefuel.ui.theme.LoginActivityTheme

class TransactionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShowTransaction(innerPadding = innerPadding)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ShowTransactionDemo(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ShowTransaction(innerPadding = innerPadding)
    }
}


@Composable
fun ShowTransaction(innerPadding : PaddingValues){

    Column {
        Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "Final Transaction Details Page", fontSize = 32.sp)
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