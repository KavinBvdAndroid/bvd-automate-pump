package com.example.loginactivity.feature.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.LoginLogo
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.ReusableTextInput
import com.example.loginactivity.core.base.generics.isValidEmail
import com.example.loginactivity.core.base.generics.isValidPassword
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.ui.theme.LoginActivityTheme

class LoginActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginActivityTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = colorResource(id = R.color.white)
                ) { innerPadding ->
                    MainContent(innerPadding)
                }
            }
        }
        hideSystemUI()
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentDemo() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        MainContent(innerPadding)
    }

}

@Composable
private fun MainContent(innerPadding: PaddingValues) {
    var loginEmail by rememberSaveable { mutableStateOf("") }
    var isEmailValid by rememberSaveable { mutableStateOf(false) }
    var loginPassword by rememberSaveable { mutableStateOf("") }
    var isPasswordValid by rememberSaveable { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        LoginLogo()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = LocalConfiguration.current.screenHeightDp.dp * 0.7f)
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ReusableTextInput(
                value = loginEmail,
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { newValue ->
                    loginEmail = newValue
                    isEmailValid = newValue.isValidEmail()
                },
                label = stringResource(id = R.string.l_email),
                isError = loginEmail.isNotBlank() && !isEmailValid,
                errorMessage = stringResource(id = R.string.e_email),
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            ReusableTextInput(
                value = loginPassword,
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { newValue ->
                    loginPassword = newValue
                    isPasswordValid = newValue.isValidPassword()
                },
                label = stringResource(id = R.string.l_password),
                isError = loginPassword.isNotBlank() && !isPasswordValid,
                errorMessage = stringResource(id = R.string.e_password),
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            ReusableElevatedButton(
                onClick = {
                    AppUtils.showToastMessage("Validated...")
                        context.startActivity(Intent(context, VinNumberActivityCompose::class.java))
                },
                text = "Login",
                isEnabled = isEmailValid && isPasswordValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

        }

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

