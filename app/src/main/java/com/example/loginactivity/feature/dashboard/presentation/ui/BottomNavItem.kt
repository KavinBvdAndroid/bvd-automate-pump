package com.example.loginactivity.feature.dashboard.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem (val route: String, val icon: ImageVector, val title: String){
    object Location : BottomNavItem("Location", Icons.Default.LocationOn, "Home")
    object TransactionHistory : BottomNavItem("TransactionHistory", Icons.Default.CreditCard, "Transactions")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")


}