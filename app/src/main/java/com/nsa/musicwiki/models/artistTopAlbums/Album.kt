package com.nsa.musicwiki.models.artistTopAlbums

import com.nsa.musicwiki.models.topAlbums.Artist
import com.nsa.musicwiki.models.topAlbums.Image

data class Album(
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val playcount: Int,
    val url: String
)