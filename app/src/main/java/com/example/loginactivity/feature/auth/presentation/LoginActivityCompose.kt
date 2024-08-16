package com.example.loginactivity.feature.auth.presentation

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.ErrorAlertDialog
import com.example.loginactivity.core.base.generics.GenericProgressBar
import com.example.loginactivity.core.base.generics.LoginLogo
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.ReusableTextInput
import com.example.loginactivity.core.base.generics.isValidUsername
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.auth.data.model.LoginResponse
import com.example.loginactivity.feature.auth.presentation.viewmodel.LoginViewModel
import com.example.loginactivity.feature.vinnumber.VinNumberActivityCompose
import com.example.loginactivity.ui.theme.LoginActivityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = colorResource(id = R.color.white)
                ) { innerPadding ->
                    LoginContent(innerPadding)
                }
            }
        }
        hideSystemUI()
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginContentDemo() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorResource(id = R.color.white)
        ) { innerPadding ->
            LoginContent(innerPadding)
        }
    }

}

@Composable
private fun LoginContent(innerPadding: PaddingValues) {
    val viewModel: LoginViewModel = hiltViewModel()

    var loginEmail by rememberSaveable { mutableStateOf("") }
    var isEmailValid by rememberSaveable { mutableStateOf(false) }
    var loginPassword by rememberSaveable { mutableStateOf("") }
    var isPasswordValid by rememberSaveable { mutableStateOf(false) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val loginResult by viewModel.userDetails.observeAsState(null)
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val scrollState = rememberScrollState()

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
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { newValue ->
                    loginEmail = newValue
                    isEmailValid = newValue.isValidUsername()
                },
                label = stringResource(id = R.string.l_userName),
                isError = loginEmail.isNotBlank() && !isEmailValid,
                errorMessage = stringResource(id = R.string.e_user_name),
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            ReusableTextInput(
                value = loginPassword,
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { newValue ->
                    loginPassword = newValue
                    isPasswordValid = newValue.isNotEmpty() && newValue.isNotBlank()
                },
                label = stringResource(id = R.string.l_password),
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth(),
                isPassword = true,
                isPasswordVisible = isPasswordVisible,
                onPasswordVisibilityChange = { isPasswordVisible = it }
            )

            ReusableElevatedButton(
                onClick = {
                    viewModel.loginUserEmail(loginEmail, loginPassword)
                },
                text = "Login",
                isEnabled = isEmailValid && isPasswordValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

        }

    }
    loginResult?.let { ObserveLoginResult(it) }
}

@Composable
fun ObserveLoginResult(loginResult: Resource<LoginResponse>) {
    val context = LocalContext.current

    when (loginResult) {
        is Resource.Loading -> {
            GenericProgressBar(true)
        }

        is Resource.Failure -> {
            GenericProgressBar(false)
            CustomErrorAlertDialog(showDg = true, loginResult) {}
        }

        is Resource.Success -> {
            GenericProgressBar(false)
            LaunchedEffect(Unit) {
                context.startActivity(Intent(context, VinNumberActivityCompose::class.java))
            }
        }

        else -> {}

    }
}

@Composable
fun CustomErrorAlertDialog(
    showDg: Boolean,
    loginResult: Resource.Failure,
    dismissDialogCallback: () -> Unit
) {

    ErrorAlertDialog(
        title = "Error \n ${loginResult.errorBody?.code ?: ""}",
        message = "${loginResult.errorBody?.message ?: loginResult.message} error occurred. Please try again later.",
        buttonText = "OK",
        onDismiss = { },
        titleBackgroundColor = Color.Red, // Custom title background color
        titleCornerRadius = 16 // Rounded corner radius
    )

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

