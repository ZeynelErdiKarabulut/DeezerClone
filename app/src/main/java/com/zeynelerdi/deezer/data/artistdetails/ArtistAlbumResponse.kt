package com.zeynelerdi.deezer.data.artistdetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistAlbumResponse(
    @SerialName("data")
    val `data`: List<ArtistAlbumData>,
    val next: String="",
    val total: Int
)