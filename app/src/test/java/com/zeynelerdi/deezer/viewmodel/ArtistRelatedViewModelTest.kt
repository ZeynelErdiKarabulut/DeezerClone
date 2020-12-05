package com.zeynelerdi.deezer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.zeynelerdi.deezer.core.MockUtil
import com.zeynelerdi.deezer.core.Result
import com.zeynelerdi.deezer.data.artistdetails.ArtistRelatedData
import com.zeynelerdi.deezer.di.MainCoroutinesRule
import com.zeynelerdi.deezer.domain.local.DeezerDao
import com.zeynelerdi.deezer.domain.network.DeezerClient
import com.zeynelerdi.deezer.domain.network.DeezerService
import com.zeynelerdi.deezer.repository.DeezerRepository
import com.zeynelerdi.deezer.ui.artistdetails.related.ArtistRelatedViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistRelatedViewModelTest {
    private lateinit var viewModel: ArtistRelatedViewModel
    private lateinit var mainRepository: DeezerRepository
    private val deezerService: DeezerService = mockk()
    private val deezerClient = DeezerClient(deezerService)
    private val deezerDao: DeezerDao = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        mainRepository = DeezerRepository(deezerClient,deezerDao)
        viewModel = ArtistRelatedViewModel(mainRepository)
    }

    @Test
    fun fetchArtistDetailsTest() = runBlocking {
        val mockList = listOf(MockUtil.artistRelatedData)

        val observer : Observer<Result<List<ArtistRelatedData>>> = mock()
        val fetchedData : LiveData<Result<List<ArtistRelatedData>>> = mainRepository.fetchArtistRelated(MockUtil.artistID).asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchArtistRelated(MockUtil.artistID)
        delay(500L)

        verify(observer).onChanged(Result.Succes(mockList))
        fetchedData.removeObserver(observer)
    }

}