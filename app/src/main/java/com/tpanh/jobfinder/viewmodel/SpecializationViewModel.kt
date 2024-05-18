package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Category
import com.tpanh.jobfinder.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpecializationViewModel(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    private val _categories = MutableStateFlow(emptyList<Category>())
    val categories = _categories.asStateFlow()

    var query by mutableStateOf("")

    init {
        getCategories()
    }

    fun onQueryChange(query: String) {
        this.query = query
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            val categories = categoryRepository.searchCategory(query)
            _categories.value = categories
        }
    }

}
