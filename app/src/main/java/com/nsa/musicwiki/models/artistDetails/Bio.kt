package com.nsa.musicwiki.models.artistDetails

data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
)