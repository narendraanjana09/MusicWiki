package com.nsa.musicwiki.models.albumDetails

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.topAlbums.Artist
import com.nsa.musicwiki.models.topAlbums.Attr
import com.nsa.musicwiki.models.topTracks.Streamable

data class Track(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: Artist,
    val duration: Int,
    val name: String,
    val streamable: Streamable,
    val url: String
)