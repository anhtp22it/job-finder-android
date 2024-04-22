package com.tpanh.jobfinder.model

data class JobInfor(
    val name: String = "",
) {
    constructor(name: Map<String, String>) : this(
        name = name["common"] ?: "",
    )
}
