package com.nsa.musicwiki.models.artistTopAlbums

import com.google.gson.annotations.SerializedName
import com.nsa.musicwiki.models.artistTopTracks.Attr

data class Topalbums(

    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)