package com.zeynelerdi.deezer.data.artistdetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistRelatedResponse(
    @SerialName("data")
    val data: List<ArtistRelatedData>,
    val total: Int
)