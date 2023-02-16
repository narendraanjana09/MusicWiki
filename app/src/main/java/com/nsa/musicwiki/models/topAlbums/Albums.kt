package com.nsa.musicwiki.models.topAlbums

import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)