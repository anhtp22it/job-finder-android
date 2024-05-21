package com.tpanh.jobfinder.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Language(
    @SerialName("name")
    val name: Name,
    val flag: String = "",
)

@Serializable
data class Name(
    @SerialName("common")
    val nameCommon: String
)
