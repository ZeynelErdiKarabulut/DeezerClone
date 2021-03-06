package com.zeynelerdi.deezer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.zeynelerdi.deezer.core.MockUtil
import com.zeynelerdi.deezer.core.Result
import com.zeynelerdi.deezer.data.genre.Data
import com.zeynelerdi.deezer.di.MainCoroutinesRule
import com.zeynelerdi.deezer.domain.local.DeezerDao
import com.zeynelerdi.deezer.domain.network.DeezerClient
import com.zeynelerdi.deezer.domain.network.DeezerService
import com.zeynelerdi.deezer.repository.DeezerRepository
import com.zeynelerdi.deezer.ui.genre.GenreViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GenreViewModelTest {
    private lateinit var viewModel: GenreViewModel
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
        viewModel = GenreViewModel(mainRepository)
    }

    @Test
    fun fetchGenreListTest() = runBlocking {
        val mockList = MockUtil.genres
        whenever(deezerDao.getGenreList()).thenReturn(mockList)

        val observer : Observer<Result<List<Data>>> = mock()
        val fetchedData : LiveData<Result<List<Data>>> = mainRepository.fetchGenreList().asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchResult()
        delay(500L)

        verify(deezerDao, atLeastOnce()).getGenreList()
        verify(observer).onChanged(Result.Succes(mockList))
        fetchedData.removeObserver(observer)


    }



}