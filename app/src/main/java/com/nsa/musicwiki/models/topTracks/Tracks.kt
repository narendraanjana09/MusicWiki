package com.nsa.musicwiki.models.topTracks

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.topAlbums.Attr

data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)