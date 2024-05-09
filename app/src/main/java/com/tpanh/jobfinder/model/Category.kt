package com.tpanh.jobfinder.model

import com.google.firebase.database.PropertyName

data class Category(
    var uid: String = "",
    var category: String = "",
    var image: String = "",
    var subCategories: List<String> = emptyList()
)
