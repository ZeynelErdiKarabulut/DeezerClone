package com.zeynelerdi.deezer

import com.zeynelerdi.deezer.domain.local.DeezerDaoTest
import com.zeynelerdi.deezer.domain.network.DeezerServiceTest
import com.zeynelerdi.deezer.repository.MainRepositoryTest
import com.zeynelerdi.deezer.viewmodel.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DeezerDaoTest::class,
    DeezerServiceTest::class,
    MainRepositoryTest::class,
    DeezerDaoTest::class,
    AlbumDetailsViewModelTest::class,
    ArtistAlbumViewModelTest::class,
    ArtistDetailsViewModelTest::class,
    ArtistViewModelTest::class,
    FavoritesViewModelTest::class,
    GenreViewModelTest::class,
    MainViewModelTest::class,
    SearchViewModelTest::class,
)
class SuiteClass