package com.nsa.musicwiki.models.topArtists

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.topAlbums.Attr

data class Top_artists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)