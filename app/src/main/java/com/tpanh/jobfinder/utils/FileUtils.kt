package com.tpanh.jobfinder.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

fun getFileName(uri: Uri, contentResolver: ContentResolver): String? {
    return try {
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val displayNameColumnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (displayNameColumnIndex != -1) {
                    cursor.getString(displayNameColumnIndex)
                } else {
                    null
                }
            } else {
                null
            }
        } ?: uri.lastPathSegment
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun getFileSize(uri: Uri?, contentResolver: ContentResolver): Long? {
    if (uri == null) return null

    var fileSize: Long? = null
    var cursor: Cursor? = null

    try {
        if (uri.scheme == "file") {
            val file = File(uri.path ?: "")
            if (file.exists()) {
                fileSize = file.length()
            }
        } else {
            cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor?.moveToFirst() == true) {
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                if (!cursor.isNull(sizeIndex)) {
                    fileSize = cursor.getLong(sizeIndex)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        cursor?.close()  // Close cursor only if it was created
    }

    return fileSize
}

fun formatBytes(bytes: Long): String {
    val units = arrayOf("B", "Kb", "Mb", "Gb", "Tb")
    var unitIndex = 0
    var formattedBytes = bytes.toDouble()
    while (formattedBytes >= 1024 && unitIndex < units.size - 1) {
        formattedBytes /= 1024
        unitIndex++
    }
    return "${Math.round(formattedBytes)} ${units[unitIndex]}"
}