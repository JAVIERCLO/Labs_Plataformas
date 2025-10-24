package com.gt.uvg.rickandmorty.data

import com.gt.uvg.rickandmorty.data.LocationDto
import com.gt.uvg.rickandmorty.data.database.entities.LocationEntity

fun LocationDto.toEntity(): LocationEntity {
    return LocationEntity(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension
    )
}
