package com.zeynelerdi.deezer.ui.artistdetails.albums

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeynelerdi.deezer.core.Result
import com.zeynelerdi.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistAlbumViewModel @ViewModelInject constructor(
    private val mainRepository: DeezerRepository
):ViewModel(){

    var result:LiveData<Result<Any>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    init {
        Timber.d("init viewModel..")
    }

    fun fetchArtistAlbum(artistID:String){
        viewModelScope.launch {
            try {
                result = mainRepository.fetchArtistAlbums(artistID)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }

}