package com.tpanh.jobfinder.repository.impl

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.tpanh.jobfinder.repository.ImageRepository
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ImageRepositoryImpl(
    private val storage: FirebaseStorage
): ImageRepository {

    override suspend fun uploadImage(imageUri: Uri, folder: String): String {
        val documentId = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("$folder/$documentId.jpg")
        val uploadTask = imageRef.putFile(imageUri)

        uploadTask.await()

        val downloadUrl = imageRef.downloadUrl.await()

        return downloadUrl.toString()
    }


    override suspend fun deleteImage(imageUrl: String) {
        val imageRef = storage.getReferenceFromUrl(imageUrl)
        imageRef.delete().await()
    }
}