package com.example.loginactivity.feature.maps.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.GenericProgressBar
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.feature.ui.theme.LoginActivityTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriverLocationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FetchLocationContent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentDemo() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController)
        SideCp(it)

    }

}

@Composable
fun SideCp(inner:PaddingValues){

}

@SuppressLint("MissingPermission")
@Composable
fun FetchCurrentLocation(
    onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
    onGetCurrentLocationFailed: (Exception) -> Unit,
    priority: Boolean = true
) {

    val context = LocalContext.current
    var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    // Determine the accuracy priority based on the 'priority' parameter
    val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
    else Priority.PRIORITY_BALANCED_POWER_ACCURACY

    fusedLocationProviderClient.getCurrentLocation(
        accuracy,
        CancellationTokenSource().token
    ).addOnSuccessListener { location ->
        location?.let {
            onGetCurrentLocationSuccess(Pair(it.latitude, it.longitude))
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Location.route) {
        composable(BottomNavItem.Location.route) {
            FetchLocationContent()
        }
        composable(BottomNavItem.TransactionHistory.route) {
            SearchScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Location,
        BottomNavItem.TransactionHistory,
        BottomNavItem.Profile
    )


    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


@Composable
fun SearchScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text("Search Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text("Profile Screen")
    }
}

@Composable
fun FetchLocationContent() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var isFetchLocation by rememberSaveable { mutableStateOf(false) }
    var driverLocation by rememberSaveable { mutableStateOf<Location?>(null) }
    var showProgress by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier

            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = customTextStyle.titleLarge,
            text = stringResource(id = R.string.h_driver_location_warning),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        )

//
        ElevatedButton(
            onClick = {
                isFetchLocation = true
            },
            modifier = Modifier
                .size(260.dp) // Adjust size as needed
                .background(Color.Blue, shape = CircleShape), // Background color and shape
            shape = CircleShape, // Button shape

            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = colorResource(id = R.color.colorOnPrimary),
                contentColor = Color.White,
                disabledContainerColor = colorResource(id = R.color.disabledStartButtonColor),
            ), // Button colors
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 16.dp,
                pressedElevation = 10.dp// Adjust shadow elevation as needed
            ),
            contentPadding = PaddingValues(0.dp) // Remove padding to make it perfectly circular
        ) {
            Text(
                text = "Start fetching site locations",
                color = Color.White, // Text color
                modifier = Modifier.padding(10.dp) // Adjust padding for text
            )
        }
    }

    if (isFetchLocation) {
        GenericProgressBar(true)
        FetchUserLocation {
            driverLocation = it
        }
    }

    if (driverLocation != null) {
        GenericProgressBar(false)
        LaunchedEffect(true) {

            val intent = Intent()
            context.startActivity(
                Intent(context, MapsSiteActivity::class.java)
                    .putExtra("latitude", driverLocation!!.latitude)
                    .putExtra("longitude", driverLocation!!.longitude)
            )
        }
    }
}

@Composable
fun FetchUserLocation(locationCallback: (location: Location) -> Unit) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Location?>(null) }
    var locationPermissionGranted by remember { mutableStateOf(false) }

    // Request location permission
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            locationPermissionGranted = isGranted
            if (isGranted) {
                fetchLocation(fusedLocationClient) {
                    location = it
                }
            }
        }
    )

    LaunchedEffect(true) {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    if (location != null) {
        locationCallback(location!!)
    } else {
        Text("Fetching location...")
    }
}

@SuppressLint("MissingPermission")
fun fetchLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationFetched: (Location?) -> Unit
) {
//    fusedLocationClient.getCurrentLocation()
//        .addOnSuccessListener { location: Location? ->
//            onLocationFetched(location)
//        }
//        .addOnFailureListener {
//            onLocationFetched(null)
//        }

    fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        CancellationTokenSource().token
    ).addOnSuccessListener { location ->
        location?.let {
            onLocationFetched(location)
        }
    }.addOnFailureListener {
        onLocationFetched(null)
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