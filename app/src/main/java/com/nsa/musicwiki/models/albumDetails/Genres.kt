package com.nsa.musicwiki.models.albumDetails

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("tag")
    val genre: List<Genre>
)