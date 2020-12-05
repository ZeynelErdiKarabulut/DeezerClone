package com.zeynelerdi.deezer.domain.network

import javax.inject.Inject

class DeezerClient @Inject constructor(
        private val deezerService: DeezerService
){

    fun fetchGenreList()
            = deezerService.fetchGenreList()

    fun fetchArtistList(genreId:String)
            = deezerService.fetchArtistList(genreId)

    fun fetchArtistDetails(artistID: String)
            = deezerService.fetchArtistDetails(artistID)

    fun fetchArtistAlbums(artistID: String)
            = deezerService.fetchArtistAlbums(artistID)

    fun fetchArtistRelated(artistID: String)
            = deezerService.fetchArtistRelated(artistID)

    fun fetchAlbumDetails(albumID:String)
            = deezerService.fetchAlbumDetails(albumID)

    fun fetchSearchAlbum(q:String)
            = deezerService.fetchSearchAlbum(q)
}