package com.nsa.musicwiki.models.Genre

import com.google.gson.annotations.SerializedName

data class TopGenresResponse(
    @SerializedName("toptags")
    val topGenres: TopGenres
)