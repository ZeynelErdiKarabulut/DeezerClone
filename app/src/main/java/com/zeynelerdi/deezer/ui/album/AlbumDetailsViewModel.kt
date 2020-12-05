package com.zeynelerdi.deezer.ui.album

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeynelerdi.deezer.core.Result
import com.zeynelerdi.deezer.data.albumdetails.AlbumData
import com.zeynelerdi.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumDetailsViewModel @ViewModelInject constructor(
    private val mainRepository: DeezerRepository
):ViewModel(){

    var result:LiveData<Result<Any>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    init {
        Timber.d("init...")
    }

    fun favoritedToTrack(data:AlbumData) {
        viewModelScope.launch {
            mainRepository.insertFavoritesData(track = data)
        }
    }

    fun fetchingAlbumDatas(albumID:String){
        viewModelScope.launch {
            try{
                result = mainRepository.fetchAlbumTracks(albumID)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }
}