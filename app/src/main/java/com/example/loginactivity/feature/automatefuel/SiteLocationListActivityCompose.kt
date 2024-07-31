package com.example.loginactivity.feature.automatefuel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.automatefuel.ui.theme.LoginActivityTheme

class SiteLocationListActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting4(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        hideSystemUI()
    }
}
@Preview(showBackground = true)
@Composable
fun SiteLocationListDemo() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        SiteLocationListContent(innerPadding)
    }

}

@Composable
fun SiteLocationListContent(innerPadding: PaddingValues) {
    MainScreen()
}

@Composable
fun OptimizedLazyColumn(items: List<String>, scrollState: LazyListState) {
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        itemsIndexed(items) { index, item ->
            ListItem(item = item, index = index)
        }
    }
}

@Composable
fun ListItem(item: String, index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        BasicText(
            text = "Item $index: $item",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MainScreen() {
    val items = List(100) { "Item $it" } // Sample data
    val scrollState = rememberLazyListState()

    OptimizedLazyColumn(items = items, scrollState = scrollState)
}

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    LoginActivityTheme {
        Greeting4("Android")
    }
}