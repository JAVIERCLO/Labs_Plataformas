package com.gt.uvg.rickandmorty.data

import com.gt.uvg.rickandmorty.data.Api
import com.gt.uvg.rickandmorty.data.LocationDto
import com.gt.uvg.rickandmorty.data.HttpClientFactory
import io.ktor.client.call.body
import io.ktor.client.request.get

class LocationsApi(
    private val clientFactory: () -> io.ktor.client.HttpClient = { HttpClientFactory.create() }
) {
    private val client by lazy { clientFactory() }

    suspend fun getLocations(page: Int = 1): Result<Api<LocationDto>> {
        return try {
            val response = client.get("location?page=$page")
            val payload: Api<LocationDto> = response.body()
            Result.success(payload)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}