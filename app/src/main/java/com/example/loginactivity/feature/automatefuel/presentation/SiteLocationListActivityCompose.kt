package com.example.loginactivity.feature.automatefuel.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.loginactivity.core.base.generics.ReusableElevatedButton
import com.example.loginactivity.core.base.utils.AppUtils
import com.example.loginactivity.core.base.utils.AppUtils.hideSystemUI
import com.example.loginactivity.feature.automatefuel.data.model.SiteDetails
import com.example.loginactivity.feature.automatefuel.data.model.driverLocation
import com.example.loginactivity.feature.automatefuel.data.model.sortedListOfSites
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

class SiteLocationListActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SiteLocationListContent(innerPadding)
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
        SiteLocationListContent(innerPadding)
    }

}

@Composable
fun SiteLocationListContent(innerPadding: PaddingValues) {
    MapsBottomSheet()
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
    context.startActivity(intent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsBottomSheet() {
    val context = LocalContext.current


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 15f)
    }
    val markerClicked by rememberSaveable { mutableStateOf(false) }
    var selectedSite by rememberSaveable { mutableStateOf(sortedListOfSites.first()) }
    var showInfoWindow by remember { mutableStateOf(false) }

    var selectedSiteId by rememberSaveable { mutableStateOf(sortedListOfSites.first().siteId) }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val initialHeight = screenHeight * 0.1f
    val fullHeight = screenHeight // Full screen height

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            LocationListBottomSheet(locations = sortedListOfSites, selectedSite = selectedSite) {
                showInfoWindow = true
            }
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
                    val markerState = markerStates[site]!!

                    Marker(
                        state = markerState,
                        title = site.name,
                        snippet = "${site.distanceToLocation} km away",
                        onInfoWindowClick = {
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
    var newSelectedSite by rememberSaveable { mutableStateOf<SiteDetails?>(selectedSite) }
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        locations.forEach { site ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (newSelectedSite != null) {
                    RadioButton(
                        modifier = Modifier.size(24.dp),
                        selected = site.siteId == newSelectedSite?.siteId,
                        onClick = {
                            newSelectedSite = site
                            onLocationSelected(newSelectedSite!!)
                        })
                }
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
            Divider()
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