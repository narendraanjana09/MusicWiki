package com.nsa.musicwiki.models.artistDetails

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.albumDetails.Genres
import com.nsa.musicwiki.models.topAlbums.Image


data class Artist(
    val bio: Bio,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val ontour: String,
    val similar: Similar,
    val stats: Stats,
    val streamable: String,
    @SerializedName("tags")
    val genres: Genres,
    val url: String
)