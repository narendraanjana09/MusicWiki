package com.nsa.musicwiki.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nsa.musicwiki.api.ApiResult
import com.nsa.musicwiki.models.Genre.Genre
import com.nsa.musicwiki.models.GenreDetail.GenreDetail
import com.nsa.musicwiki.models.albumDetails.AlbumDetails
import com.nsa.musicwiki.models.artistDetails.ArtistDetails
import com.nsa.musicwiki.models.artistTopAlbums.ArtistTopAlbums
import com.nsa.musicwiki.models.artistTopTracks.ArtistTopTracks
import com.nsa.musicwiki.models.topAlbums.TopAlbums
import com.nsa.musicwiki.models.topArtists.TopArtists
import com.nsa.musicwiki.models.topTracks.TopTracks
import com.nsa.musicwiki.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String>
        get() = _message

    private val _genresList: MutableLiveData<List<Genre>> = MutableLiveData()
    val genresList: LiveData<List<Genre>>
        get() = _genresList

    private val _genresInfo: MutableLiveData<com.nsa.musicwiki.models.GenreDetail.Genre> = MutableLiveData()
    val genresInfo: LiveData<com.nsa.musicwiki.models.GenreDetail.Genre>
        get() = _genresInfo

    private val _topAlbums: MutableLiveData<TopAlbums> = MutableLiveData()
    val topAlbums: LiveData<TopAlbums>
        get() = _topAlbums

    private val _topTracks: MutableLiveData<TopTracks> = MutableLiveData()
    val topTracks: LiveData<TopTracks>
        get() = _topTracks

    private val _topArtists: MutableLiveData<TopArtists> = MutableLiveData()
    val topArtists: LiveData<TopArtists>
        get() = _topArtists

    private val _albumDetails: MutableLiveData<AlbumDetails> = MutableLiveData()
    val albumDetails: LiveData<AlbumDetails>
        get() = _albumDetails

    private val _artistDetails: MutableLiveData<ArtistDetails> = MutableLiveData()
    val artistDetails: LiveData<ArtistDetails>
        get() = _artistDetails

    private val _artistTopTracks: MutableLiveData<ArtistTopTracks> = MutableLiveData()
    val artistTopTracks: LiveData<ArtistTopTracks>
        get() = _artistTopTracks

    private val _artistTopAlbums: MutableLiveData<ArtistTopAlbums> = MutableLiveData()
    val artistTopAlbums: LiveData<ArtistTopAlbums>
        get() = _artistTopAlbums


    fun getTopGenres(){
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getTopGenres()
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _genresList.value=response?.data?.topGenres?.genres
                }
                else -> {}
            }

        }
    }
    fun getGenreInfo(genre:String){
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getGenreInfo(genre)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _genresInfo.value=response?.data?.genre
                }
                else -> {}
            }

        }
    }

    fun getTopAlbums(genreName: String) {
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getTopAlbums(genreName)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _topAlbums.value=response?.data!!
                }
                else -> {}
            }

        }
    }
    fun getTopTracks(genreName: String) {
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getTopTracks(genreName)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _topTracks.value=response?.data!!
                }
                else -> {}
            }

        }
    }

    fun getTopArtists(genreName: String) {
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getTopArtists(genreName)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _topArtists.value=response?.data!!
                }
                else -> {}
            }

        }
    }

    fun getAlbumDetails(artist: String,albumName:String) {
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getAlbumDetails(artist,albumName)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _albumDetails.value=response?.data!!
                }
                else -> {}
            }

        }
    }

    fun getArtistDetails(artist: String) {
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getArtistDetails(artist)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _artistDetails.value=response?.data!!
                }
                else -> {}
            }

        }
    }

    fun getArtistTopTracks(artist: String) {
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getArtistTopTracks(artist)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _artistTopTracks.value=response?.data!!
                }
                else -> {}
            }

        }
    }

    fun getArtistTopAlbums(artist: String) {
        _loading.value=true
        viewModelScope.launch {
            val response = MainRepository().getArtistTopAlbums(artist)
            Log.e("TAG", "genres_response $response: ", )
            _loading.value = false
            when (response.status) {
                ApiResult.Status.ERROR -> {
                    _message.value = response.message!!
                }
                ApiResult.Status.SUCCESS -> {
                    _artistTopAlbums.value=response?.data!!
                }
                else -> {}
            }

        }
    }

}