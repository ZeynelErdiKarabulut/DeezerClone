package com.zeynelerdi.deezer.ui.artistdetails.detail

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeynelerdi.deezer.core.Result
import com.zeynelerdi.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistDetailsViewModel @ViewModelInject constructor(
    val mainRepository: DeezerRepository
):ViewModel() {

    var result: LiveData<Result<Any>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    init {
        Timber.d("view model init.")
    }


    fun fetchArtistDetails(artistID:String){
        viewModelScope.launch {
            try {
                result = mainRepository.fetchArtistDetails(artistID)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }

}