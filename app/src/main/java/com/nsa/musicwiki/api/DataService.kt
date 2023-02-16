package com.nsa.musicwiki.api


import com.nsa.musicwiki.models.Genre.TopGenresResponse
import com.nsa.musicwiki.models.GenreDetail.GenreDetail
import com.nsa.musicwiki.models.albumDetails.AlbumDetails
import com.nsa.musicwiki.models.artistDetails.ArtistDetails
import com.nsa.musicwiki.models.artistTopAlbums.ArtistTopAlbums
import com.nsa.musicwiki.models.artistTopTracks.ArtistTopTracks
import com.nsa.musicwiki.models.topAlbums.TopAlbums
import com.nsa.musicwiki.models.topArtists.TopArtists
import com.nsa.musicwiki.models.topTracks.TopTracks
import retrofit2.Response
import retrofit2.http.*

interface DataService {

    @GET("?method=tag.getTopTags&format=json")
    suspend fun getTopGenres(@Query("api_key") api_key:String): Response<TopGenresResponse>

    @GET("?method=tag.getinfo&format=json")
    suspend fun getGenreInfo(@Query("api_key") api_key:String,
                             @Query("tag") genre:String
                             ): Response<GenreDetail>

    @GET("?method=tag.gettopalbums&format=json")
    suspend fun getTopAlbums(@Query("api_key") api_key:String,
                             @Query("tag") genre:String
    ): Response<TopAlbums>

    @GET("?method=tag.gettoptracks&format=json")
    suspend fun getTopTracks(@Query("api_key") api_key:String,
                             @Query("tag") genre:String
    ): Response<TopTracks>

    @GET("?method=tag.gettopartists&format=json")
    suspend fun getTopArtists(@Query("api_key") api_key:String,
                             @Query("tag") genre:String
    ): Response<TopArtists>

    @GET("?method=album.getinfo&format=json")
    suspend fun getAlbumDetails(@Query("api_key") api_key:String,
                              @Query("artist") artist:String,
                                @Query("album") album:String
    ): Response<AlbumDetails>

    @GET("?method=artist.getInfo&format=json")
    suspend fun getArtistDetails(@Query("api_key") api_key:String,
                                @Query("artist") artist:String
    ): Response<ArtistDetails>

    @GET("?method=artist.gettoptracks&format=json")
    suspend fun getArtistTopTracks(@Query("api_key") api_key:String,
                                   @Query("artist") artist:String
    ): Response<ArtistTopTracks>

    @GET("?method=artist.gettopalbums&format=json")
    suspend fun getArtistTopAlbums(@Query("api_key") api_key:String,
                                   @Query("artist") artist:String
    ): Response<ArtistTopAlbums>
}
