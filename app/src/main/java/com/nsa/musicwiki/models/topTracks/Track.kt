package com.nsa.musicwiki.models.topTracks

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.topAlbums.Artist
import com.nsa.musicwiki.models.topAlbums.AttrX
import com.nsa.musicwiki.models.topAlbums.Image

data class Track(
    @SerializedName("@attr")
    val attr: AttrX,
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)