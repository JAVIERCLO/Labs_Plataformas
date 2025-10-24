package com.gt.uvg.rickandmorty.data


import kotlinx.serialization.Serializable

@Serializable
data class PageInfoDto(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: String? = null
)

@Serializable
data class Api<T>(
    val info: PageInfoDto? = null,
    val results: List<T> = emptyList()
)