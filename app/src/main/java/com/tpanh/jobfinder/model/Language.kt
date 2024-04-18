package com.tpanh.jobfinder.model

data class Language(
    val id: Int,
    val name: String,
    val flag: String
) {
    constructor(id: Int, name: Map<String, String>, flag: String) : this(
        id = id,
        name = name["common"] ?: "",
        flag = flag
    )
}
