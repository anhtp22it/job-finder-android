package com.tpanh.jobfinder.repository

import android.graphics.Bitmap
import android.net.Uri

interface ImageRepository {
    suspend fun uploadImage(imageUri: Uri, folder: String): String
    suspend fun deleteImage(imageUrl: String)
}