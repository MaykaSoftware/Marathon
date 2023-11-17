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
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(text = "Swipe up to expand sheet")
        }
        Text(
            text = "${state.marathonRace?.raceDistance ?: "0"} km",
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = "${state.marathonRace?.name}",
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = "${state.marathonRace?.raceName}",
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = "${state.marathonRace?.date}",
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
        Text(
            text = "${state.marathonRace?.countOfParticipants}",
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
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                isMyLocationEnabled = true,
                isTrafficEnabled = true
            )
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxSize()) {
            if (state.marathonRace?.coordinates?.isNotEmpty() == true) {
                CameraPosition(
                    mapProperties = mapProperties,
                    lineType = LineType.POLYLINE,
                    state = state
                )

            } else {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun CameraPosition(
    mapProperties: MapProperties = MapProperties(
        isMyLocationEnabled = true
    ),
    lineType: LineType = LineType.POLYLINE,
    state: State
) {

    val latLngList by remember {
        mutableStateOf(state.marathonRace?.coordinates)
    }

    state.currentLocation?.let { location ->
        val coordinate = if (!latLngList.isNullOrEmpty()) LatLng(
            latLngList!![0].latitude,
            latLngList!![0].longitude
        ) else
            LatLng(
                location.latitude, location.longitude
            )

        val cameraPosition = rememberCameraPositionState {
            position = CameraPosition.Builder().zoom(12f)
                .target(
                    coordinate
                ).build()
        }

        AnimateCamera(state = state, cameraPosition = cameraPosition, coordinate = coordinate)

        LoadMap(
            latLngList = latLngList,
            cameraPosition = cameraPosition,
            mapProperties = mapProperties,
            lineType = lineType,
            state = state
        )
    }
}

@Composable
fun AnimateCamera(
    state: State,
    cameraPosition: CameraPositionState,
    coordinate: LatLng
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = state.marathonRace?.coordinates, block = {
        scope.launch {
            cameraPosition.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition.Builder()
                        .zoom(12f)
                        .target(coordinate)
                        .build()
                ), 2000
            )
        }
    })
}

@Composable
fun LoadMap(
    latLngList: List<LatLng>?,
    cameraPosition: CameraPositionState,
    mapProperties: MapProperties,
    lineType: LineType,
    state: State
) {
    Box {
        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.matchParentSize(),
                cameraPositionState = cameraPosition,
                properties = mapProperties
            ) {
                if (lineType == LineType.POLYLINE) {
                    Polyline(points = latLngList!!, color = Color.Red)
                }
                if (state.marathonRace != null && state.marathonRace.coordinates.first().latitude == state.marathonRace.coordinates.last().latitude &&
                    state.marathonRace.coordinates.first().longitude == state.marathonRace.coordinates.last().longitude
                ) {
                    Marker(
                        state = MarkerState(position = state.marathonRace.coordinates.first()),
                        title = state.marathonRace.raceName,
                        snippet = "Start and Finish",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                    )
                } else if (state.marathonRace != null) {
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
        }
    }
}

enum class LineType {
    POLYLINE
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMapScreen() {
    MapScreen(state = State())
}