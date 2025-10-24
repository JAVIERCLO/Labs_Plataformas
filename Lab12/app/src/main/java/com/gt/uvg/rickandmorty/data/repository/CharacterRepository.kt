package com.gt.uvg.rickandmorty.data.repository

import android.app.Application
import com.gt.uvg.rickandmorty.data.database.AppDatabase
import com.gt.uvg.rickandmorty.data.mappers.toEntity
import com.gt.uvg.rickandmorty.data.mappers.toModel
import com.gt.uvg.rickandmorty.data.CharactersApi
import com.gt.uvg.rickandmorty.data.toEntity
import com.gt.uvg.rickandmorty.presentation.model.CharacterUi

class CharacterRepository(
    application: Application
) {
    private val api = CharactersApi()
    private val database = AppDatabase.getDatabase(application)

    suspend fun getCharacters(): List<CharacterUi> {
        val dao = database.characterDao()
        val cached = dao.getAllCharacters()
        if (cached.isNotEmpty()) return cached.map { it.toModel() }

        api.getCharacters(page = 1).onSuccess { page ->
            dao.insertCharacters(page.results.map { it.toEntity() })
        }
        val fresh = dao.getAllCharacters()
        return fresh.map { it.toModel() }
    }

    suspend fun getCharacter(id: Int): CharacterUi {
        val characterEntity = database.characterDao().getCharacterById(id)
        return characterEntity.toModel()
    }
}