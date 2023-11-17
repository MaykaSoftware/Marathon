package com.sportunity.marathon.domain.repository

import android.annotation.SuppressLint
import android.os.Looper
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.sportunity.marathon.data.local.entity.events.ItemEntity
import com.sportunity.marathon.data.mapper.toMarathon
import com.sportunity.marathon.data.remote.dto.event.MarathonRaceDTO
import com.sportunity.marathon.data.remote.service.MarathonService
import com.sportunity.marathon.domain.model.Marathon
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarathonRepositoryImpl @Inject constructor(
    pager: Pager<Int, ItemEntity>,
    private val marathonService: MarathonService,
    private val fusedLocationProviderClient: FusedLocationProviderClient
): MarathonRepository {

    override val marathonEvents: Flow<PagingData<Marathon>> =
        pager.flow.map {
            it.map { itemEntity ->
                itemEntity.toMarathon()
            }
        }.catch {
            throw it
        }

    override suspend fun getRace(id: Int): MarathonRaceDTO {
        return marathonService.getRace(990)
    }

    @SuppressLint("MissingPermission")
    override fun getLocation() = callbackFlow{
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).setIntervalMillis(1000).build()

        val locationCallback = object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult) {
                val currentLocation = p0.lastLocation
                if (currentLocation != null) {
                    trySend(currentLocation)
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }
}