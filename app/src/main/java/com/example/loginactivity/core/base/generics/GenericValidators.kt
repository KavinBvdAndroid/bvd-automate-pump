package com.example.loginactivity.core.base.generics

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import com.example.loginactivity.core.base.utils.Constants

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    // Implement your password validation logic
    // For example: at least 8 characters, containing digits, letters, and special characters
    val passwordRegex =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
    return this.matches(passwordRegex)
}

fun String.isValidUsername(): Boolean {
    // Implement your username validation logic
    // For example: 3-20 characters, letters, numbers, and underscores only
    val usernameRegex = "^[a-zA-Z0-9_]{3,20}$".toRegex()
    return this.matches(usernameRegex)
}

fun String.isValidPhoneNumber(): Boolean {
    // Implement your phone number validation logic
    // This is a simple example and might need to be adjusted based on your requirements
    val phoneRegex = "^[+]?[0-9]{10,13}$".toRegex()
    return this.matches(phoneRegex)
}

fun String.isValidConfirmPassword(password: String): Boolean {
    return this == password
}

fun String.isValidVinNumber(): Pair<String, Boolean> {
    // Check if the string contains only letters and numbers
    if (!this.matches(Regex("^[a-zA-Z0-9]*$"))) {
        return Pair("Input can only contain letters and numbers.", false)
    }
    // Check if the string length meets the required length
    val missingCharacters = Constants.VIN_NUMBER_LENGTH - this.length
    return if (missingCharacters > 0) {
        Pair(
            "Missing $missingCharacters characters to meet the ${Constants.VIN_NUMBER_LENGTH} characters length requirement.",
            false
        )
    } else {
        Pair("", true)
    }
}

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s.toString())
        }
    })
}


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}