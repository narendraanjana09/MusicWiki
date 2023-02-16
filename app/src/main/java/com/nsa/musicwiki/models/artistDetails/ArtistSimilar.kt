package com.nsa.musicwiki.models.artistDetails

import com.nsa.musicwiki.models.topAlbums.Image

data class ArtistSimilar(
    val image: List<Image>,
    val name: String,
    val url: String
)