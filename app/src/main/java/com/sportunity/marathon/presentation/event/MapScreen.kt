package com.sportunity.marathon.presentation.event

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.sportunity.marathon.util.checkForPermission

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(state: State) {
    BottomSheetScaffold(
        sheetPeekHeight = 80.dp,
        sheetContent = {
            BottomSheetContent(state)

        }) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            CheckLocationPermission(state = state)
        }
    }
}

@Composable
fun BottomSheetContent(state: State) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(text = "Swipe up to expand sheet")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${state.marathonRace.raceDistance} km",
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = state.marathonRace.name,
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = state.marathonRace.raceName,
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = state.marathonRace.date,
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = "${state.marathonRace.countOfParticipants}",
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun CheckLocationPermission(
    modifier: Modifier = Modifier,
    state: State
) {
    val context = LocalContext.current
    var hasLocationPermission by remember { mutableStateOf(checkForPermission(context)) }
    if (hasLocationPermission) {
        MapContainer(modifier, state)
    } else {
        LocationPermissionScreen {
            hasLocationPermission = true
        }
    }
}

@Composable
fun MapContainer(
    modifier: Modifier,
    state: State
) {
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.Builder().zoom(13f)
            .target(
                state.startLocation
            ).build()
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxSize()) {
            if (state.marathonRace.coordinates.isNotEmpty()) {
                AnimateCamera(
                    state = state,
                    cameraPosition = cameraPosition,
                    coordinate = state.startLocation
                )
                LoadMap(cameraPosition = cameraPosition, state = state)
            } else {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun AnimateCamera(
    state: State,
    cameraPosition: CameraPositionState,
    coordinate: LatLng
) {
    LaunchedEffect(key1 = state.marathonRace.coordinates, block = {
        cameraPosition.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition.Builder()
                    .zoom(12f)
                    .target(coordinate)
                    .build()
            ), 2000
        )
    })
}

@Composable
fun LoadMap(
    cameraPosition: CameraPositionState,
    mapProperties: MapProperties = MapProperties(
        isMyLocationEnabled = true
    ),
    state: State
) {
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPosition,
            properties = mapProperties
        ) {
            Polyline(points = state.marathonRace.coordinates, color = Color.Red)
            SetMarker(state = state)
        }
    }
}

@Composable
fun SetMarker(state: State) {
    if (state.marathonRace.coordinates.first().latitude == state.marathonRace.coordinates.last().latitude &&
        state.marathonRace.coordinates.first().longitude == state.marathonRace.coordinates.last().longitude
    ) {
        Marker(
            state = MarkerState(position = state.marathonRace.coordinates.first()),
            title = state.marathonRace.raceName,
            snippet = "Start and Finish",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
        )
    } else {
        Marker(
            state = MarkerState(position = state.marathonRace.coordinates.first()),
            title = state.marathonRace.raceName,
            snippet = "Start",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        )
        Marker(
            state = MarkerState(position = state.marathonRace.coordinates.last()),
            title = state.marathonRace.raceName,
            snippet = "Finish",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMapScreen() {
    MapScreen(state = State())
}