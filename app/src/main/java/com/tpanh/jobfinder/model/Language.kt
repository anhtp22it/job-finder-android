package com.tpanh.jobfinder.model

data class Language(
    val name: String = "",
    val flag: String = "",
) {
    constructor(name: Map<String, String>, flag: String) : this(
        name = name["common"] ?: "",
        flag = flag
    )
}
