package com.example.loginactivity.feature.maps.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.ErrorAlertDialog
import com.example.loginactivity.core.base.generics.GenericDetailRow
import com.example.loginactivity.core.base.generics.GenericProgressBar
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.generics.TransparentTopBarWithBackButton
import com.example.loginactivity.core.base.generics.formatLatitude
import com.example.loginactivity.core.base.generics.transferToFuelSites
import com.example.loginactivity.core.base.testdatas.driverLocation
import com.example.loginactivity.core.base.testdatas.listOfSites
import com.example.loginactivity.core.base.testdatas.sortedListOfSites
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.maps.data.model.DataItem
import com.example.loginactivity.feature.maps.data.model.FetchInYardSitesResponse
import com.example.loginactivity.feature.maps.data.model.MarkerData
import com.example.loginactivity.feature.maps.domain.model.FuelSite
import com.example.loginactivity.feature.pumpoperation.data.model.SiteDetails
import com.example.loginactivity.feature.pumpoperation.presentation.ui.FuelSelectionActivity
import com.example.loginactivity.feature.pumpoperation.ui.theme.LoginActivityTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsSiteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val latLng = LatLng(latitude, longitude)

        setContent {
            LoginActivityTheme {
                SiteLocationListDemo()
            }
        }
        hideSystemUI()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SiteLocationListDemo() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TransparentTopBarWithBackButton(
                onBackClick = { backDispatcher?.onBackPressed() },
                scrollBehavior = scrollBehavior,
                topBarColor = Color.Black
            )
        }
    ) { innerPadding ->
        ShowMapsMockData(innerPadding, listOfSites)
//        FetchFuelSites(innerPadding)
    }

}

@Composable
fun FetchFuelSites(innerPadding: PaddingValues) {
    val viewModel: FuelSitesViewModel = hiltViewModel()
    viewModel.fetchNearBySites()

    val fuelSites by viewModel.fuelSiteDetails.collectAsState()

    when (fuelSites) {
        is Resource.Loading -> {
            GenericProgressBar(isLoading = true)
        }

        is Resource.Success -> ShowMapsMockData(
            innerPadding = innerPadding,
            listOfSites
        )

        is Resource.Failure -> ErrorAlertDialog(
            title = "Error",
            message = (fuelSites as Resource.Failure).message,
            buttonText = "Ok",
            onDismiss = { })

        else -> {}
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMapsMockData(innerPadding: PaddingValues, fuelSites: List<SiteDetails>) {

    val sortedFuelSites by remember(listOfSites) {
        derivedStateOf<List<SiteDetails>> { listOfSites.sortedBy { it.distanceToLocation } }
    }
    var selectedSite by remember {
        mutableStateOf<SiteDetails>(sortedFuelSites.first())
    }

    var drawMarker by rememberSaveable {
        mutableStateOf(true)
    }

    val context = LocalContext.current


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
    }

    Log.d("function Recompose", "Recomps")
    BottomSheetScaffold(
        sheetContent = {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Transparent)
            ) {
                SiteListMockApi(
                    sites = sortedListOfSites,
                    selectedSiteId = selectedSite.siteId,
                    onSelect = { site ->
                        selectedSite = site
                        Log.d("call back site list", site.siteId)
                    })
                ReusableElevatedButton(
                    onClick = {
                        context.startActivity(
                            Intent(context, FuelSelectionActivity::class.java)
                                .putExtra("selectedSite", selectedSite)

                        )
                    },
                    text = "Continue",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    isEnabled = true
                )

            }
        },
        sheetPeekHeight = 100.dp,

        sheetShape = RoundedCornerShape(topEnd = 36.dp, topStart = 36.dp),
        sheetContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
        sheetDragHandle =
        {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(Color.Transparent)
            ) {
                Divider(
                    color = MaterialTheme.colorScheme.onTertiary,
                    thickness = 4.dp,
                    modifier = Modifier
                        .width(32.dp)
                        .align(Alignment.Center)
                )
            }

        }

    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = MapType.TERRAIN),
            uiSettings = MapUiSettings(zoomControlsEnabled = true),
            onMapClick = {

            }
        ) {

            ShowDriverLocationMarker(driverLocation)

            LaunchedEffect(sortedFuelSites) {
                drawMarker = true
            }
            sortedFuelSites.forEachIndexed { index, site ->


                val markerState =
                    rememberMarkerState(position = LatLng(site.latitude, site.longitude))

                if (drawMarker) {
                    MarkerInfoWindow(
                        state = markerState,
                        title = "${site.name} : ${site.distanceToLocation} Kms",
                        snippet = "Get Directions",

                        anchor = Offset(0.5f, 0.0f),
                        onClick = {
                            selectedSite = site
                            false // Consume the click event
                        },
                        onInfoWindowClick = {
                            openGoogleMapsWithDirections(context, site.latitude, site.longitude)
                        },

                        )
                }

                LaunchedEffect(key1 = selectedSite) {
                    if (selectedSite.siteId == site.siteId) {
                        Log.d("selected site", site.siteId)
                        markerState.showInfoWindow()
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(site.latitude, site.longitude), 15f // Zoom level
                            )
                        )
                    }

                }

//                if (index == sortedFuelSites.size-1){
//                    drawMarker = false
//                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMapsRealAPI(innerPadding: PaddingValues, fuelSites: FetchInYardSitesResponse?) {

    val listOfDataItem: List<DataItem?> = fuelSites?.data?.data ?: emptyList()
    val listOfFuelSites = transferToFuelSites(listOfDataItem)

    val sortedFuelSites by remember(listOfFuelSites) {
        derivedStateOf<List<FuelSite>> { listOfFuelSites.sortedBy { it.stateId } }
    }
    var selectedSite by remember {
        mutableStateOf<FuelSite>(listOfFuelSites.first())
    }

    var drawMarker by rememberSaveable {
        mutableStateOf(true)
    }

    val context = LocalContext.current


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
    }

    Log.d("function Recompsoe", "Recomps")
    BottomSheetScaffold(
        sheetContent = {
            Column {
                SiteList(
                    sites = sortedFuelSites,
                    selectedSiteId = selectedSite.id.toString(),
                    onSelect = { site ->
                        selectedSite = site
                        Log.d("call back site list", site.id.toString())
                    })

                ReusableElevatedButton(
                    onClick = { /*TODO*/ },
                    text = "Continue",
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = true
                )
            }


        },
        sheetPeekHeight = 100.dp,
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = MapType.TERRAIN),
            uiSettings = MapUiSettings(zoomControlsEnabled = true),
            onMapClick = {

            }
        ) {

            ShowDriverLocationMarker(driverLocation)

            LaunchedEffect(sortedFuelSites) {
                drawMarker = true
            }
            sortedFuelSites.forEachIndexed { index, site ->

                val formattedLatitude = formatLatitude(site.latitude, site.latitudeDirection)
                val formattedLongitude = formatLatitude(site.longitude, site.longitudeDirection)

                val markerState =
                    rememberMarkerState(position = LatLng(formattedLatitude, formattedLongitude))

                if (drawMarker) {
                    MarkerInfoWindow(
                        state = markerState,
                        title = "${site.name} : ${site.stateId} Kms",
                        snippet = "Get Directions",

                        anchor = Offset(0.5f, 0.0f),
                        onClick = {
                            selectedSite = site
                            false // Consume the click event
                        },
                        onInfoWindowClick = {
                            openGoogleMapsWithDirections(
                                context,
                                formattedLatitude,
                                formattedLongitude
                            )
                        },

                        )
                }

                LaunchedEffect(key1 = selectedSite) {
                    if (selectedSite.id == site.id) {
                        Log.d("selected site", site.id.toString())
                        markerState.showInfoWindow()
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(formattedLatitude, formattedLongitude), 15f // Zoom level
                            )
                        )
                    }

                }

//                if (index == sortedFuelSites.size-1){
//                    drawMarker = false
//                }
            }

        }
    }
}

@Composable
fun CustomInfoWindow(marker: Marker) {
    Column(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = marker.title ?: "No Title",
            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = marker.snippet ?: "No Snippet",
            style = TextStyle(color = Color.Black)
        )
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ShowMapsWithCustomInfoWindow(innerPadding: PaddingValues) {
//
//    val sortedFuelSites by remember(listOfSites) {
//        derivedStateOf { listOfSites.sortedBy { it.distanceToLocation } }
//    }
//    var selectedSite by rememberSaveable {
//        mutableStateOf<SiteDetails>(sortedFuelSites.first())
//    }
//
//    var drawMarker by rememberSaveable {
//        mutableStateOf(true)
//    }
//
//var markerState = rememberMarkerState()
//    var marker = Marker()
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
//    }
//
//    Log.d("function Recompsoe", "Recomps")
//    BottomSheetScaffold(
//        sheetContent = {
//            SiteList(
//                sites = sortedFuelSites,
//                selectedSiteId = selectedSite.siteId,
//                onSelect = { site ->
//                    selectedSite = site
//                    Log.d("call back site list", site.siteId)
//                })
//
//        },
//        sheetPeekHeight = 500.dp,
//    ) {
//        GoogleMap(
//            modifier = Modifier.fillMaxSize(),
//            cameraPositionState = cameraPositionState,
//            properties = MapProperties(mapType = MapType.TERRAIN),
//            uiSettings = MapUiSettings(zoomControlsEnabled = true)
//        ) {
//
//            ShowDriverLocationMarker(driverLocation)
//
//            sortedFuelSites.forEach { site ->
//                key(site.siteId) {
//                     markerState =
//                        rememberMarkerState(position = LatLng(site.latitude, site.longitude))
//                    Marker(
//                        state = markerState,
//                        title = site.name,
//                        snippet = site.address,
//                        onClick = {
//                            selectedSite = site
//                            true // Return true to consume the click event
//                        }
//                    )
//                }
//            }
//        }
//
//        selectedSite.let {
//            LaunchedEffect(key1 = it) {
//               CustomInfoWindow(marker = markerState. )
//            }
//        }
//
//
//    }
//}


@Composable
fun FuelSiteMarker(
    site: SiteDetails,
    isSelected: Boolean,
    sortedFuelSites: List<SiteDetails>,
    onSelect: (SiteDetails) -> Unit
) {
    val markerState = rememberMarkerState(position = LatLng(site.latitude, site.longitude))

    LaunchedEffect(isSelected) {
        if (isSelected) {
            markerState.showInfoWindow()
        } else {
            markerState.hideInfoWindow()
        }
    }

    LaunchedEffect(sortedFuelSites) {

    }
    Marker(
        state = markerState,
        title = site.name,
        snippet = site.siteId,
        onClick = {
            onSelect(site)
            true // Return true to consume the click event
        }
    )
}


@Composable
fun ShowFuelSitesLocationMarker(
    fuelSites: List<SiteDetails>, selectedSite: SiteDetails,
    selectedCallback: (SiteDetails) -> Unit,
) {
    val context = LocalContext.current
    var markerStateGlobal = rememberMarkerState()


    val sortedFuelSites by remember(fuelSites) {
        derivedStateOf {
            fuelSites.sortedBy {
                it.distanceToLocation
            }
        }
    }

    sortedFuelSites.forEach { site ->
        key(site.siteId) {
            FuelSiteMarker(
                site,
                isSelected = site.siteId == selectedSite.siteId,
                selectedSite,
                onSelect = selectedCallback
            )
        }

    }


}

@Composable
fun FuelSiteMarker(
    site: SiteDetails,
    isSelected: Boolean,
    selectedSite: SiteDetails,
    onSelect: (SiteDetails) -> Unit
) {
    val markerState = rememberMarkerState(position = LatLng(site.latitude, site.longitude))

//    LaunchedEffect(markerState.position, key2 = isSelected) {
//        if (isSelected) {
//            markerState.showInfoWindow()
//        } else {
//            markerState.hideInfoWindow()
//        }
//    }

    Marker(
        state = markerState,
        title = site.name,
        snippet = site.address,
        onClick = {
            onSelect(site)
            false
        }
    )

    LaunchedEffect(isSelected) {
        if (selectedSite.siteId == site.siteId && isSelected) {
            markerState.showInfoWindow()
        }
    }
}

@Composable
fun CustomInfoWindowContent(site: SiteDetails) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .width(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = site.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            Text(
                text = site.address,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Distance: ${site.distanceToLocation} km",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ShowDriverLocationMarker(driverLocation: LatLng) {
    val context = LocalContext.current
    val customBitmap = getBitmapFromVectorDrawable(context, R.drawable.pin)
    Marker(
        state = MarkerState(position = driverLocation),
        title = "BVD Driver Location",
        snippet = "Marker",
        icon = BitmapDescriptorFactory.fromBitmap(customBitmap)
    )
}


fun openGoogleMapsWithLocation(context: Context, latitude: Double, longitude: Double) {
    val uri = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    context.startActivity(intent)
}

fun openGoogleMapsWithDirections(context: Context, latitude: Double, longitude: Double) {
    val uri = Uri.parse("google.navigation:q=$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

    context.startActivity(intent)
}


@Composable
fun SiteListMockApi(
    sites: List<SiteDetails>,
    selectedSiteId: String,
    onSelect: (SiteDetails) -> Unit
) {

    var selectedId = selectedSiteId
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 56.dp)
    ) {
        items(items = sites, key = { item ->
            item.siteId.toString()
        }) { site ->

            Card(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    ,
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    selectedId = site.siteId.toString()
                },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {

                RadioButtonWithSiteMockData(
                    site = site,
                    isSelected = site.siteId == selectedId,
                    onSelect = {
                        onSelect(site)
                    }
                )
            }


        }

    }

}


@Composable
fun SiteList(
    sites: List<FuelSite?>,
    selectedSiteId: String,
    onSelect: (FuelSite) -> Unit
) {

    var selectedId = selectedSiteId
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(items = sites, key = { item ->
            item?.id.toString()
        }) { site ->

            Card(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    if (site != null) {
                        selectedId = site.id.toString()
                    }
                },
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {

                if (site != null) {
                    RadioButtonWithSite(
                        site = site,
                        isSelected = site.id.toString() == selectedId,
                        onSelect = {
                            onSelect(site)
                        }
                    )
                }


            }

        }
    }
}


@Composable
fun GoogleMapView(
    markers: List<MarkerData>,
    onMarkerClick: (MarkerData) -> Unit
) {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        markers.forEach { markerData ->
            val markerState =
                rememberMarkerState(position = markerData.position)
            Marker(
                state = markerState,
                title = markerData.title,
                snippet = markerData.snippet,
                onClick = {
                    onMarkerClick(markerData)
                    true
                }
            )

        }
    }
}


//    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
//    val coroutineScope = rememberCoroutineScope()
//
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    val initialHeight = screenHeight * 0.1f
//    val fullHeight = screenHeight //
//    val selectedSite by rememberSaveable { mutableStateOf(sortedListOfSites.first()) }
//    Log.d("Selected site",""+selectedSite.address)
//    var showInfoWindow by remember { mutableStateOf(false) }
//
//    val driverLocation by viewModel.driverLocationFlow.collectAsState()
//    val fuelSiteDetails by viewModel.fuelSiteDetails.collectAsState()
//
//    val sortedSites by viewModel.sites.collectAsState()
//    val markerStates by viewModel.markerStates.collectAsState()
//
//    var selectedSiteA by rememberSaveable {
//        mutableStateOf(sortedSites.first())
//    }
//
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(latLng!!, 15f)
//    }
//    var selectedSitec by remember { mutableStateOf<SiteDetails?>(null) }
//
//    BottomSheetScaffold(
//        scaffoldState = bottomSheetScaffoldState,
//        sheetContent = {
////            LocationListBottomSheet(locations = sortedListOfSites, selectedSite = selectedSiteA) {
////                selectedSiteA = it
////            }
//            LazyColumn(modifier = Modifier.padding(8.dp)) {
//                items(sortedSites.size) { site ->
//                    RadioButtonWithSite(
//                        site = sortedSites[site],
//                        isSelected = sortedSites[site].siteId == selectedSitec?.siteId,
//                        onSelect = { selectedSitec = sortedSites[site] }
//                    )
//                }
//            }
//        },
//        sheetSwipeEnabled = true,
//        sheetPeekHeight = initialHeight,
//        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
//
//        modifier = Modifier.padding(0.dp),
//        sheetContainerColor = colorResource(id = R.color.Transparent)
//
//
//
//        ) {
//
//        GoogleMap(
//            modifier = Modifier.fillMaxSize(),
//            cameraPositionState = cameraPositionState,
//            properties = MapProperties(mapType = MapType.TERRAIN),
//            uiSettings = MapUiSettings(zoomControlsEnabled = true)
//        ) {
//            ShowDriverLocation(driverLocation = driverLocation)
//            sortedSites.forEach { site ->
//                val markerState = rememberMarkerState(position = LatLng(site.latitude, site.longitude))
//
//                Marker(
//                    state = markerState,
//                    title = site.name,
//                    snippet = site.distanceToLocation.toString(),
//                    onClick = {
//                        selectedSitec = site
//                        markerState.showInfoWindow()
//                        true
//                    }
//                )
//
//                // Show info window if this marker's site is selected
//                if (selectedSitec?.siteId == site.siteId) {
//                    markerState.showInfoWindow()
//                }
//            }
//        }
//    }

@Composable
fun InfoWindow(marker: MarkerData, onDismiss: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .background(color = colorResource(id = R.color.Transparent))
            .width(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = marker.title, style = MaterialTheme.typography.labelMedium)
            Text(text = marker.snippet, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    }
}

@Composable
fun RadioButtonWithSiteMockData(site: SiteDetails, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onSelect() }
            .padding(vertical = 8.dp)
            .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.background(Color.Transparent)) {
            GenericDetailRow(label = "Id :", value = site.siteId)
            site.name.let { GenericDetailRow(label = "Name :", value = it) }
            GenericDetailRow(
                label = "Distance :",
                value = site.distanceToLocation.toString() + " Kms"
            )
            site.address.let { GenericDetailRow(label = "Address :", value = it) }
        }
    }
}


@Composable
fun RadioButtonWithSite(site: FuelSite, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onSelect() }
            .padding(vertical = 8.dp)
            .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (modifier = Modifier.background(Color.Transparent)){
            GenericDetailRow(label = "Id :", value = site.id.toString())
            site.name.let { GenericDetailRow(label = "Name :", value = it) }
            GenericDetailRow(
                label = "Distance :",
                value = site.city.toString() + " Kms"
            )
            GenericDetailRow(label = "Address :", value = site.address)
        }
    }
}


@Composable
fun ShowFuelSitesLocation(selectedSiteCallback: (selectedSiteB: SiteDetails) -> Unit) {
    val context = LocalContext.current

    val derivedSortedListOfSites by remember {
        derivedStateOf { sortedListOfSites }
    }

    val markerStates = remember(derivedSortedListOfSites) {
        derivedSortedListOfSites.associateWith { site ->
            MarkerState(position = LatLng(site.latitude, site.longitude))
        }
    }

    val selectedSite by remember { mutableStateOf(sortedListOfSites.first()) }
    var showInfoWindow by remember { mutableStateOf(true) }

    sortedListOfSites.forEach { site ->
        val markerState = markerStates[site]!!

        Marker(
            state = markerState,
            title = site.name,
            snippet = "${site.distanceToLocation} km away",
            onInfoWindowClick = {
                showInfoWindow = false
                openGoogleMapsWithDirections(context, it.position.latitude, it.position.longitude)
            }
        )
        if (selectedSite == site && showInfoWindow) {
            Log.d("seleted site", "" + selectedSite.distanceToLocation)
            LaunchedEffect(site.name) {
                markerState.showInfoWindow()
                Log.d("seleted site", "" + selectedSite.distanceToLocation)
                showInfoWindow = false
            }
        }
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
    val vectorDrawable = context.getDrawable(drawableId)
    vectorDrawable?.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap = Bitmap.createBitmap(
        vectorDrawable!!.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = android.graphics.Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return Bitmap.createScaledBitmap(bitmap, 100, 100, false)
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