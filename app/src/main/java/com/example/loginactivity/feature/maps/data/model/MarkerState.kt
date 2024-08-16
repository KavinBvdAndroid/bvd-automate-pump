package com.example.loginactivity.feature.maps.data.model

import com.google.android.gms.maps.model.LatLng

data class MarkerData(
                      val id: String,
                      val position: LatLng,
                      val title: String,
                      val snippet: String
)
