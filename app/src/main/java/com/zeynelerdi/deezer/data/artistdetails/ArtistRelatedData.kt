package com.zeynelerdi.deezer.data.artistdetails

import kotlinx.serialization.Serializable

@Serializable
data class ArtistRelatedData(
    val id: String,
    val link: String,
    val name: String,
    val nb_album: Int,
    val nb_fan: Int,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)