package com.nsa.musicwiki.models.artistTopTracks

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.topAlbums.Artist
import com.nsa.musicwiki.models.topAlbums.AttrX
import com.nsa.musicwiki.models.topAlbums.Image

data class Track(

    @SerializedName("@attr")
    val attr: AttrX,
    val artist: Artist,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val streamable: String,
    val url: String
)