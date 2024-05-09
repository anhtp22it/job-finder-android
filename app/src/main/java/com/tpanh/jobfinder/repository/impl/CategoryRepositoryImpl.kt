package com.tpanh.jobfinder.repository.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.Category
import com.tpanh.jobfinder.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class CategoryRepositoryImpl(
    private val fireStore: FirebaseFirestore
): CategoryRepository {
    override suspend fun getCategories(): Flow<List<Category>> {
        return flow {
            val categories = fireStore.collection("categories")
                .get()
                .await()
                .documents
                .map { it.toObject(Category::class.java)!! }
            emit(categories)
        }
    }
}