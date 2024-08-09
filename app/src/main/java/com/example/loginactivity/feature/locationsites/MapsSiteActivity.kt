package com.example.loginactivity.feature.locationsites

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginactivity.R
import com.example.loginactivity.core.base.generics.GenericDetailRow
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.automatefuel.data.model.SiteDetails
import com.example.loginactivity.feature.automatefuel.data.model.driverLocation
import com.example.loginactivity.feature.automatefuel.data.model.sortedListOfSites
import com.example.loginactivity.feature.automatefuel.presentation.FuelSelectionActivity
import com.example.loginactivity.feature.automatefuel.ui.theme.LoginActivityTheme
import com.google.android.gms.maps.model.BitmapDescriptor
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
                    ModalBottomSheetDemo(innerPadding, latLng)
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
        ModalBottomSheetDemo(innerPadding, null)
//        BottomSheetWithMapAndSiteList(
//            sites = sortedListOfSites,
//            selectedSiteId = sortedListOfSites.first().siteId,
//            onSelect = { selectedId ->
//
//            },
//            innerPadding
//        )
    }

}

@Composable
fun SiteLocationListContent(innerPadding: PaddingValues, latLng: LatLng) {
    ModalBottomSheetDemo(innerPadding, latLng)

}

@Composable
fun MapsContent() {

    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
    }
    val markerClicked by rememberSaveable { mutableStateOf(false) }
    val selectedSite by rememberSaveable { mutableStateOf<SiteDetails?>(null) }

    var selectedSiteId by rememberSaveable { mutableStateOf(sortedListOfSites.first().siteId) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.TERRAIN),
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        val driverMarkerState = rememberMarkerState(position = driverLocation)

        Marker(
            state = driverMarkerState,
            title = "My location",
            snippet = "BVD Group",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
            onInfoWindowClick = { driverMarkerState.showInfoWindow() },

            )

        if (markerClicked) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {
                Button(
                    onClick = {
                        // Implement your own logic to view location or open navigation
                        selectedSite?.let {
                            openGoogleMapsWithLocation(context, it.latitude, it.longitude)
                        }
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text("View Location")
                }

                Button(
                    onClick = {
                        // Implement your own logic to get directions
                        selectedSite?.let {
                            openGoogleMapsWithDirections(context, it.latitude, it.longitude)
                        }
                    }
                ) {
                    Text("Get Directions")
                }
            }
        }

        sortedListOfSites.forEach { site ->
            val markerState = rememberMarkerState(position = LatLng(site.latitude, site.longitude))
            Marker(
                state = markerState,
                title = site.name,
                snippet = "${site.distanceToLocation} km away",
                onInfoWindowClick = {
                    markerState.showInfoWindow()
                    selectedSiteId = site.siteId
                }
            )
//            markerState.showInfoWindow()
            driverMarkerState.showInfoWindow()

        }
    }

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
                    onSelect = { onSelect(site)}
                )


            }

        }
    }
}

@Composable
fun BottomSheetWithMapAndSiteList(
    sites: List<SiteDetails>,
    selectedSiteId: String,
    onSelect: (String) -> Unit,
    innerPadding: PaddingValues
    // Assuming you have a map state for managing the map
) {

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetDemo(innerPadding: PaddingValues, latLng: LatLng?) {

    val viewModel: FuelSitesViewModel = hiltViewModel()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng!!, 15f)
    }
//    val cameraPositionState = rememberCameraPositionState()


    if (latLng != null) {
        viewModel.saveUserLocation(latLng.latitude, latLng.longitude)
    } else {
        AppUtils.showToastMessage("Lat lng Null")
    }

    viewModel.updateSites(sortedListOfSites)

    val sortedFuelSites = sortedListOfSites
    var selected_site by rememberSaveable { mutableStateOf<SiteDetails?>(sortedFuelSites.first()) }

    val markerStates = remember(sortedListOfSites) {
        sortedListOfSites.associateWith { site ->
            MarkerState(position = LatLng(site.latitude, site.longitude))
        }
    }

    BottomSheetScaffold(
        sheetContent = {
            selected_site?.let { SiteList(sites = sortedFuelSites, selectedSiteId = it.siteId, onSelect = {
                selected_site= it
            }) }

        },
        sheetPeekHeight = 100.dp,
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            if (latLng != null) {
                ShowDriverLocation(DriverLocation(latLng.latitude,latLng.longitude))
            }
            sortedFuelSites.forEach { site ->
                val markerState =markerStates[site] ?: rememberMarkerState(position = LatLng(site.latitude, site.longitude))

                Marker(
                    state = markerState,
                    title = site.name,
                    snippet = site.distanceToLocation.toString(),
                    onClick = {
                        selected_site = site
                        true
                    },

                )
                if (selected_site?.siteId == site.siteId) {
                    LaunchedEffect (site.siteId){
                        markerState.showInfoWindow()
                    }

                }
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
}

@Composable
fun RadioButtonWithSite(site: SiteDetails, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onSelect() }
            .padding(vertical = 8.dp)
            , verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            GenericDetailRow(label = "Id :", value = site.siteId)
            GenericDetailRow(label = "Name :", value = site.name)
            GenericDetailRow(label = "Distance :", value = site.distanceToLocation.toString() +" Kms")
            GenericDetailRow(label = "Address :", value = site.address)
        }
    }
}

@Composable
fun ShowDriverLocation(driverLocation: DriverLocation) {
    val context = LocalContext.current
    val customBitmap = getBitmapFromVectorDrawable(context, R.drawable.pin)

    driverLocation.let {
        val marker = rememberMarkerState(position = LatLng(it.latitude, it.longitude))

        Marker(
            state = marker,
            title = "Driver location",
            snippet = "BVD Group",
            icon = BitmapDescriptorFactory.fromBitmap(customBitmap),
        )
        marker.showInfoWindow()
    }

}

@Composable
fun ShowFuelSiteLocation(
    fuelSiteDetails: List<SiteDetails>, markerStates: Map<String, MarkerState>,
    selectedSiteCallback: (selectedSiteB: SiteDetails) -> Unit
) {

    Log.d("maps location", "Recompose outer")

    fuelSiteDetails.forEach { site ->
        val markerState = markerStates[site.siteId] ?: rememberMarkerState(
            position = LatLng(
                site.latitude,
                site.longitude
            )
        )

        Marker(
            state = markerState,
            title = site.name,
            snippet = site.distanceToLocation.toString(),
            onClick = {
                selectedSiteCallback(site)
                markerState.showInfoWindow()
                true
            }
        )
        Log.d("maps location", "Recompose")
//        if (selectedSiteB?.id == site.id) {
//            markerState.showInfoWindow()
//        }
        // Optional: Show info window based on user interaction
    }

//    val derivedSortedListOfSites by remember {
//        derivedStateOf { fuelSiteDetails.sortedBy { it.distanceToLocation } }
//    }
//    // Use remember to keep marker states stable
//
//    val markerStates = remember(derivedSortedListOfSites) {
//        derivedSortedListOfSites.associateWith { site ->
//            MarkerState(position = LatLng(site.latitude, site.longitude))
//        }
//    }

//    val finalMarkerState = rememberMarkerState(markerStates[s])
//    derivedSortedListOfSites.forEach {site->
//        val markerState = markerStates[site]!!

//        val markerState = markerStates.value.getOrPut(site.siteId) {
//            rememberMarkerState(position = LatLng(site.latitude, site.longitude))
//        }

//        Marker(
//            state = markerState,
//            title = site.name,
//            snippet = site.distanceToLocation.toString(),
//        )
//
//    }

}

@Composable
fun ShowDriverLocationMap(latLng: LatLng) {
    val context = LocalContext.current

    val latitude = rememberSaveable { mutableDoubleStateOf(latLng.latitude) }
    val longitude = rememberSaveable { mutableDoubleStateOf(latLng.longitude) }
    val driverLatLng by derivedStateOf { LatLng(latitude.doubleValue, longitude.doubleValue) }

    val driverMarkerState = remember { MarkerState(position = driverLatLng) }
    val customBitmap = getBitmapFromVectorDrawable(context, R.drawable.pin)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.TERRAIN),
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        Marker(
            state = driverMarkerState,
            title = "Driver location",
            snippet = "BVD Group",
            icon = BitmapDescriptorFactory.fromBitmap(customBitmap),
        )
        LaunchedEffect(key1 = driverLatLng.latitude, key2 = driverLatLng.longitude) {
            driverMarkerState.showInfoWindow()
        }
//        ShowFuelSitesLocation()
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsBottomSheet() {
    val context = LocalContext.current
    val markerClicked by rememberSaveable { mutableStateOf(false) }
    var selectedSite by rememberSaveable { mutableStateOf(sortedListOfSites.first()) }
    var showInfoWindow by remember { mutableStateOf(false) }
    var selectedSiteId by rememberSaveable { mutableStateOf(sortedListOfSites.first().siteId) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
    }


    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val initialHeight = screenHeight * 0.2f
    val fullHeight = screenHeight // Full screen height

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
//            LocationListBottomSheet(locations = sortedListOfSites, selectedSite = selectedSite) {
//                showInfoWindow = true
//            }
        },
        sheetPeekHeight = initialHeight,
        sheetShape = MaterialTheme.shapes.medium,
        sheetShadowElevation = 8.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.TERRAIN),
                uiSettings = MapUiSettings(zoomControlsEnabled = true)
            ) {
                val driverMarkerState = rememberMarkerState(position = driverLocation)
                // Define marker states outside the loop
                val markerStates = remember(sortedListOfSites) {
                    sortedListOfSites.associateWith { site ->
                        MarkerState(position = LatLng(site.latitude, site.longitude))
                    }
                }
                Marker(
                    state = driverMarkerState,
                    title = "My location",
                    snippet = "BVD Group",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                    onInfoWindowClick = { driverMarkerState.showInfoWindow() },

                    )
                driverMarkerState.showInfoWindow()


                if (markerClicked) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Button(
                            onClick = {
                                // Implement your own logic to view location or open navigation
                                selectedSite.let {
                                    openGoogleMapsWithLocation(context, it.latitude, it.longitude)
                                }
                            },
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Text("View Location")
                        }

                        Button(
                            onClick = {
                                // Implement your own logic to get directions
                                selectedSite.let {
                                    openGoogleMapsWithDirections(context, it.latitude, it.longitude)
                                }
                            }
                        ) {
                            Text("Get Directions")
                        }
                    }
                }

                sortedListOfSites.forEach { site ->
                    val markerState = markerStates[site]!!

                    Marker(
                        state = markerState,
                        title = site.name,
                        snippet = "${site.distanceToLocation} km away",
                        onInfoWindowClick = {
                            openGoogleMapsWithDirections(
                                context,
                                selectedSite.latitude,
                                selectedSite.longitude
                            )
                        },
                        onClick = {
                            selectedSite = site
                            showInfoWindow = true
                            true
                        }
                    )
                    if (selectedSite == site && showInfoWindow) {
                        LaunchedEffect(markerState) {
                            markerState.showInfoWindow()
                            showInfoWindow = false
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun LocationListBottomSheet(
    locations: List<SiteDetails>,
    selectedSite: SiteDetails?,
    onLocationSelected: (SiteDetails) -> Unit
) {
    val newSelectedSite by remember {
        derivedStateOf { mutableStateOf(selectedSite) }
    }
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .wrapContentSize()
            .verticalScroll(scrollState)
    ) {
        locations.forEach { site ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.size(24.dp),
                    selected = site.siteId == newSelectedSite.value?.siteId,
                    onClick = {
                        newSelectedSite.value = site
                        onLocationSelected(newSelectedSite.value!!)
                    })
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = site.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLocationSelected(site) }
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = site.address,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLocationSelected(site) }
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Distance : ${site.distanceToLocation} Kms",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLocationSelected(site) }
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }

            }
            HorizontalDivider()
        }

        ReusableElevatedButton(
            onClick = {
                AppUtils.showToastMessage("Validated...")
                context.startActivity(Intent(context, FuelSelectionActivity::class.java))
            },
            text = "Continue",
            isEnabled = selectedSite != null || newSelectedSite != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}

fun getBitmapDescriptorFromDrawable(drawableResId: Int, context: Context): BitmapDescriptor {
    val drawable: Drawable = ContextCompat.getDrawable(context, drawableResId)!!
    val bitmap: Bitmap = (drawable as BitmapDrawable).bitmap
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

fun calculateDistance(driverLocation: LatLng, destinations: List<SiteDetails>) {

}

@Composable
fun MainScreen() {
    val items = List(100) { "Item $it" } // Sample data
    val scrollState = rememberLazyListState()

    OptimizedLazyColumn(items = items, scrollState = scrollState)
}

@Composable
fun OptimizedLazyColumn(items: List<String>, scrollState: LazyListState) {
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        itemsIndexed(items) { index, item ->
            ListItem(item = item, index = index)
        }
    }
}

@Composable
fun ListItem(item: String, index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        BasicText(
            text = "Item $index: $item",
            modifier = Modifier.fillMaxWidth()
        )
    }
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