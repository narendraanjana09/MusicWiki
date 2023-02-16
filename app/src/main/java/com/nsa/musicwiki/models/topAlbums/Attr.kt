package com.nsa.musicwiki.models.topAlbums

import com.google.gson.annotations.SerializedName

data class Attr(
    val page: String,
    val perPage: String,
    @SerializedName("tag")
    val genre: String,
    val total: String,
    val totalPages: String
)