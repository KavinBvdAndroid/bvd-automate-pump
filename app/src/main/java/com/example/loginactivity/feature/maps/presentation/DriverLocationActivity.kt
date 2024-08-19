package com.example.loginactivity.feature.maps.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.GenericDetailRow
import com.example.loginactivity.core.base.generics.GenericProgressBar
import com.example.loginactivity.core.base.generics.GenericShadowHeader
import com.example.loginactivity.core.base.generics.TransparentTopBarWithBackButton
import com.example.loginactivity.core.base.generics.customTextStyle
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.auth.presentation.LoginActivityCompose
import com.example.loginactivity.ui.theme.LoginActivityTheme
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
                MainContentDemo()
            }
        }
        hideSystemUI()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainContentDemo() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
      val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MyNavigationBar(navController = navController)
        }, topBar = {
            TransparentTopBarWithBackButton(
                onBackClick = {
                    (context as? Activity)?.finish()
                },
                scrollBehavior = scrollBehavior,
                topBarColor = Color.Transparent
            )
        }
    ) {
        BottomNavGraph(navController = navController, it)
    }

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
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomNavItem.Location.route) {
        composable(BottomNavItem.Location.route) {
            FetchLocationContent(paddingValues)
        }
        composable(BottomNavItem.TransactionHistory.route) {
            TransactionHistoryScreen(paddingValues)
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen(paddingValues)
        }
    }
}


@Composable
fun MyNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Location,
        BottomNavItem.TransactionHistory,
        BottomNavItem.Profile
    )
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar(
        containerColor = colorResource(id = R.color.colorOnPrimary), // Background color
        tonalElevation = 4.dp // Elevation of the bar
    ) {
        val currentRoute = currentRoute(navController)
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        // Pop up to the start destination and clear all entries above it
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true // Optional: include the start destination in the pop
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.colorPrimaryVariant), // Color for selected icon
                    unselectedIconColor = Color.White, // Color for unselected icon
                    selectedTextColor = colorResource(id = R.color.colorPrimaryVariant), // Color for selected text
                    unselectedTextColor = Color.White // Color for unselected text
                ),
                alwaysShowLabel = true // Whether to always show labels or only for selected item
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
fun TransactionHistoryScreen(innerPadding: PaddingValues) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        GenericShadowHeader("Transaction History", Modifier.fillMaxWidth(), TextAlign.Center)
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.coming_soon),
            contentDescription = "Your image description",
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            modifier = Modifier.size(220.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun ProfileScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val viewmodel: DriverLocationViewModel = hiltViewModel()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {


        GenericShadowHeader("Driver Profile", Modifier.fillMaxWidth(), TextAlign.Center)
        Spacer(modifier = Modifier.width(16.dp))
        Column (modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {


        GenericDetailRow(label = "Name", value = "testUser")
        GenericDetailRow(label = "Email", value = "testUser@gmail.com")
        GenericDetailRow(label = "Driver Id", value = "1456")
        Spacer(modifier = Modifier.width(16.dp))
//        Image(
//            painter = painterResource(id = R.drawable.coming_soon),
//            contentDescription = "Your image description",
//            contentScale = ContentScale.Fit,
//            alignment = Alignment.Center,
//            modifier = Modifier.size(220.dp),
//        )
        ElevatedButton(onClick = {
            val isCleared = viewmodel.clearSharedPref()
            if (isCleared) {
                context.startActivity(Intent(context, LoginActivityCompose::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                })
                (context as? Activity)?.finish()
            }
        }) {
            Text(text = "Log out")
        }
        Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun FetchLocationContent(paddingValues: PaddingValues) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var isFetchLocation by rememberSaveable { mutableStateOf(false) }
    var driverLocation by rememberSaveable { mutableStateOf<Location?>(null) }
    var showProgress by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(scrollState),

        ) {
        Column(
            modifier = Modifier.heightIn(LocalConfiguration.current.screenHeightDp.dp * 0.2f),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(36.dp))
            GenericShadowHeader(
                label = "Start Your Journey",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(16.dp))
//            GenericShadowHeader(
//                label = "When You Are Ready",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 30.dp)
//                    .align(AbsoluteAlignment.Right),
//                textAlign = TextAlign.Start
//            )
        }

//
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .heightIn(LocalConfiguration.current.screenHeightDp.dp * 0.5f)
                .align(Alignment.CenterHorizontally) // Center horizontally
        ) {


            ElevatedButton(
                onClick = {
                    isFetchLocation = true
                },
                modifier = Modifier
                    .align(Alignment.Center)
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
                    text = "Let's Start",
                    color = Color.White, // Text color
                    modifier = Modifier.padding(10.dp),
                    style = customTextStyle.displayLarge// Adjust padding for text
                )
            }
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
            Log.d(
                "driver location intent",
                "${driverLocation?.latitude} and ${driverLocation?.longitude}"
            )

            context.startActivity(
                Intent(context, MapsSiteActivity::class.java)
                    .putExtra("latitude", driverLocation?.latitude)
                    .putExtra("longitude", driverLocation?.longitude)
            )
            isFetchLocation = false
            driverLocation = null
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
                fetchLocation(fusedLocationClient, onLocationFetched = {
                    location = it
                },
                    onLocationNotFetched = {
                        AppUtils.showToastMessage(it)
                    }

                )
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
    onLocationFetched: (Location?) -> Unit,
    onLocationNotFetched: (String) -> Unit
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
        Log.d("driver location", "${location.latitude} and ${location.longitude}")
        location?.let {
            onLocationFetched(location)
        }
    }.addOnFailureListener {
        onLocationNotFetched(it.message.toString())
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