package com.nsa.musicwiki.models.albumDetails

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.topAlbums.Image

data class Album(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    @SerializedName("tags")
    val genres: Genres,
    val tracks: Tracks,
    val url: String,
    val wiki: Wiki
)