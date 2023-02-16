package com.nsa.musicwiki.models.GenreDetail

data class Genre(
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)