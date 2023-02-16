package com.nsa.musicwiki.models.Genre

import com.google.gson.annotations.SerializedName


data class TopGenres(

    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("tag")
    val genres: List<Genre>
)