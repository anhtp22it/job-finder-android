package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<List<Category>>
}