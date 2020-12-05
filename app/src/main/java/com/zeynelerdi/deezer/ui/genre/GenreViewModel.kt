package com.zeynelerdi.deezer.ui.genre

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeynelerdi.deezer.core.Result
import com.zeynelerdi.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class GenreViewModel @ViewModelInject constructor(
    private val mainRepository: DeezerRepository
):ViewModel() {

    var result: LiveData<Result<Any>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    init {
        Timber.d("view model init.")
    }

    fun fetchResult(){
        viewModelScope.launch {
            try {
                result = mainRepository.fetchGenreList()
                    .asLiveData(viewModelScope.coroutineContext + Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }

}