package com.gt.uvg.rickandmorty.data

import com.gt.uvg.rickandmorty.data.CharacterDto
import com.gt.uvg.rickandmorty.data.database.entities.CharacterEntity

fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        image = this.image
    )
}