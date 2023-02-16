package com.nsa.musicwiki.models.GenreDetail

import com.google.gson.annotations.SerializedName

data class GenreDetail(
    @SerializedName("tag")
    val genre: Genre
)