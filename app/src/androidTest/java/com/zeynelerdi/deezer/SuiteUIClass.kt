package com.zeynelerdi.deezer

import com.zeynelerdi.deezer.ui.GenreFragmentTest
import com.zeynelerdi.deezer.ui.MainActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    GenreFragmentTest::class
)
class SuiteUIClass