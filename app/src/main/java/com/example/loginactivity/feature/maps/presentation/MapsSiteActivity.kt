package com.example.loginactivity.feature.maps.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.GenericDetailRow
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.maps.data.model.MarkerData
import com.example.loginactivity.feature.automatefuel.data.model.SiteDetails
import com.example.loginactivity.feature.automatefuel.data.model.driverLocation
import com.example.loginactivity.feature.automatefuel.data.model.listOfSites
import com.example.loginactivity.feature.automatefuel.data.model.sortedListOfSites
import com.example.loginactivity.feature.automatefuel.ui.theme.LoginActivityTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SiteLocationListActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val latLng = LatLng(latitude, longitude)

        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShowMaps(innerPadding)
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
        ShowMaps(innerPadding)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMaps(innerPadding: PaddingValues) {

    val viewModel : FuelSitesViewModel =hiltViewModel()

    val sortedFuelSites by remember {
        derivedStateOf { listOfSites.sortedBy { it.distanceToLocation } }
    }
    var selectedSite by remember {
        mutableStateOf<SiteDetails>(sortedFuelSites.first())
    }


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
    }

    BottomSheetScaffold(
        sheetContent = {
                SiteList(sites = sortedFuelSites, selectedSiteId = selectedSite.siteId, onSelect = {sie->
                    selectedSite = sie
                    Log.d("call back site list","${sie.siteId}")
                })

        },
        sheetPeekHeight = 100.dp,
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = MapType.TERRAIN),
            uiSettings = MapUiSettings(zoomControlsEnabled = true)
        ) {

            ShowDriverLocationMarker(driverLocation)
            selectedSite.let {
                ShowFuelSitesLocationMarker(
                    fuelSites = listOfSites,
                    selectedSite = selectedSite,
                    selectedCallback = {
                        selectedSite = it
                    })
            }
        }
    }
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

    LaunchedEffect( isSelected) {
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
fun SiteList(
    sites: List<SiteDetails>,
    selectedSiteId: String,
    onSelect: (SiteDetails) -> Unit
) {

    var sleectedId = selectedSiteId
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(items = sites, key = { item ->
            item.siteId
        }) { site ->

            Card(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    sleectedId = site.siteId
                },
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {

                RadioButtonWithSite(
                    site = site,
                    isSelected = site.siteId == sleectedId,
                    onSelect = {
                        onSelect(site)


                    }
                )


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
fun RadioButtonWithSite(site: SiteDetails, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onSelect() }
            .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            GenericDetailRow(label = "Id :", value = site.siteId)
            GenericDetailRow(label = "Name :", value = site.name)
            GenericDetailRow(
                label = "Distance :",
                value = site.distanceToLocation.toString() + " Kms"
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