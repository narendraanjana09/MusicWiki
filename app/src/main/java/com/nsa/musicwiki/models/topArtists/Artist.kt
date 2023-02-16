package com.nsa.musicwiki.models.topArtists

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.topAlbums.AttrX
import com.nsa.musicwiki.models.topAlbums.Image

data class Artist(

    @SerializedName("@attr")
    val attr: AttrX,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)