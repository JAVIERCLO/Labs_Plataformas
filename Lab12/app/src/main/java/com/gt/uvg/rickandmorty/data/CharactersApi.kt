package com.gt.uvg.rickandmorty.data

import com.gt.uvg.rickandmorty.data.Api
import com.gt.uvg.rickandmorty.data.CharacterDto
import com.gt.uvg.rickandmorty.data.HttpClientFactory
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharactersApi(
    private val clientFactory: () -> io.ktor.client.HttpClient = { HttpClientFactory.create() }
) {
    private val client by lazy { clientFactory() }

    suspend fun getCharacters(page: Int = 1): Result<Api<CharacterDto>> {
        return try {
            val response = client.get("character?page=$page")
            val payload: Api<CharacterDto> = response.body()
            Result.success(payload)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}