package com.example.loginactivity.feature.automatefuel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.feature.automatefuel.ui.theme.LoginActivityTheme

class StartFuelingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StartFuel(innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartFuelingDemo() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        StartFuel(innerPadding)
    }

}

@Composable
fun StartFuel(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()

            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(
                onClick = { AppUtils.showToastMessage("Started Fueling") },
                shape = CircleShape,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.colorOnPrimary),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .size(100.dp)
                    .padding(0.dp),

                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 8.dp // Adjust shadow elevation as needed
                ),

                ) {
                Text(" START ")
            }

            ElevatedButton(
                onClick = { AppUtils.showToastMessage("Started Fueling") },
                shape = CircleShape,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.colorError),
                    contentColor = Color.White
                ),

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
}


@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
