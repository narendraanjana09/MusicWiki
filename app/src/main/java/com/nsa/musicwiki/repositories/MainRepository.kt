package com.nsa.musicwiki.repositories

import com.nsa.musicwiki.BuildConfig
import com.nsa.musicwiki.api.ApiClient
import com.nsa.musicwiki.api.ApiResult
import com.nsa.musicwiki.api.ApiUtil
import com.nsa.musicwiki.models.Genre.TopGenresResponse
import com.nsa.musicwiki.models.GenreDetail.GenreDetail
import com.nsa.musicwiki.models.albumDetails.AlbumDetails
import com.nsa.musicwiki.models.artistDetails.ArtistDetails
import com.nsa.musicwiki.models.artistTopAlbums.ArtistTopAlbums
import com.nsa.musicwiki.models.artistTopTracks.ArtistTopTracks
import com.nsa.musicwiki.models.topAlbums.TopAlbums
import com.nsa.musicwiki.models.topArtists.TopArtists
import com.nsa.musicwiki.models.topTracks.TopTracks

class MainRepository {

    suspend fun getTopGenres(): ApiResult<TopGenresResponse> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getTopGenres(BuildConfig.API_KEY) },
            "Something went wrong",
            0
        )
    }

    suspend fun getGenreInfo(genre:String): ApiResult<GenreDetail> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getGenreInfo(BuildConfig.API_KEY,genre) },
            "Something went wrong",
            0
        )
    }
    suspend fun getTopAlbums(genre:String): ApiResult<TopAlbums> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getTopAlbums(BuildConfig.API_KEY,genre) },
            "Something went wrong",
            0
        )
    }

    suspend fun getTopTracks(genreName: String): ApiResult<TopTracks> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getTopTracks(BuildConfig.API_KEY,genreName) },
            "Something went wrong",
            0
        )
    }

    suspend fun getTopArtists(genreName: String): ApiResult<TopArtists> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getTopArtists(BuildConfig.API_KEY,genreName) },
            "Something went wrong",
            0
        )
    }

    suspend fun getAlbumDetails(artist: String,albumName:String): ApiResult<AlbumDetails> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getAlbumDetails(BuildConfig.API_KEY,artist,albumName) },
            "Something went wrong",
            0
        )
    }

    suspend fun getArtistDetails(artistName: String): ApiResult<ArtistDetails> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getArtistDetails(BuildConfig.API_KEY,artistName) },
            "Something went wrong",
            0
        )
    }

    suspend fun getArtistTopTracks(artistName: String): ApiResult<ArtistTopTracks> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getArtistTopTracks(BuildConfig.API_KEY,artistName) },
            "Something went wrong",
            0
        )
    }

    suspend fun getArtistTopAlbums(artistName: String): ApiResult<ArtistTopAlbums> {
        return ApiUtil.getResponse(
            request = { ApiClient.getDataService().getArtistTopAlbums(BuildConfig.API_KEY,artistName) },
            "Something went wrong",
            0
        )
    }
}