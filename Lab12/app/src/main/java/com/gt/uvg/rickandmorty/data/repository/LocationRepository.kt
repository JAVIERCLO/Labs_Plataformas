package com.gt.uvg.rickandmorty.data.repository

import android.app.Application
import com.gt.uvg.rickandmorty.data.database.AppDatabase
import com.gt.uvg.rickandmorty.data.mappers.toEntity
import com.gt.uvg.rickandmorty.data.mappers.toModel
import com.gt.uvg.rickandmorty.data.LocationsApi
import com.gt.uvg.rickandmorty.data.toEntity
import com.gt.uvg.rickandmorty.presentation.model.Location

class LocationRepository(
    application: Application
) {
    private val api = LocationsApi()
    private val database = AppDatabase.getDatabase(application)

    suspend fun getLocations(): List<Location> {
        val dao = database.locationDao()
        val cached = dao.getAllLocations()
        if (cached.isNotEmpty()) return cached.map { it.toModel() }

        api.getLocations(page = 1).onSuccess { page ->
            dao.insertLocations(page.results.map { it.toEntity() })
        }
        val fresh = dao.getAllLocations()
        return fresh.map { it.toModel() }
    }

    suspend fun getLocation(id: Int): Location? {
        val entity = database.locationDao().getLocationById(id)
        return entity?.toModel()
    }
}
