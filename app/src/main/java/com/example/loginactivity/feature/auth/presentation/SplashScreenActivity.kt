package com.example.loginactivity.feature.auth.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.LoginLogo
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.ui.theme.LoginActivityTheme

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SplashScreenDemo(innerPadding)
                }
            }
        }
        hideSystemUI()
    }
}

fun checkSessionManager(){

}
@Composable
fun SplashScreenDemo(innerPadding: PaddingValues) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()


    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.90f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 70.dp,
                        bottomEnd = 70.dp
                    )
                )
                .background(colorResource(id = R.color.colorOnPrimary)),

            contentAlignment = Alignment.Center
        ) {
            LoginLogo()

        }
        Box(
            modifier = Modifier
                .clickable {
                    context.startActivity(Intent(context, LoginActivityCompose::class.java))
                }
                .padding(start = 30.dp, end = 16.dp) // Optional padding
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.15f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {

                Text(
                    text = "Get  Started",
                    style = customTextStyle.displayLarge,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = "Your image description",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.size(120.dp)
                )
            }
        }
    }

}

@Composable
fun Greeting8(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    LoginActivityTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SplashScreenDemo(innerPadding)
        }

    }
}