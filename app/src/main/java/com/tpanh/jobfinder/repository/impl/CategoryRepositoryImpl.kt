package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.Category
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class CategoryRepositoryImpl(
    private val fireStore: FirebaseFirestore
): CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        return fireStore.collection("categories")
            .get()
            .await()
            .toObjects(Category::class.java)
    }

    override suspend fun searchCategory(title: String): List<Category> {
        val categories = mutableListOf<Category>()
        fireStore.collection("categories")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val category = document.toObject(Category::class.java)
                    if (category.category.contains(title, ignoreCase = true)) {
                        categories.add(category)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("JobRepositoryImpl", "Error getting documents.", exception)
            }.await()
        return categories
    }
}