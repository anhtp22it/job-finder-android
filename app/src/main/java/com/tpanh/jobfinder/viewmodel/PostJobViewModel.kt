package com.tpanh.jobfinder.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Category
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.Workplace
import com.tpanh.jobfinder.repository.CategoryRepository
import com.tpanh.jobfinder.repository.ImageRepository
import com.tpanh.jobfinder.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostJobViewModel(
    private val jobRepository: JobRepository,
    private val categoryRepository: CategoryRepository,
    private val imageRepository: ImageRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(
        Job(
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    var selectedImageUri by mutableStateOf<Uri?>(null)

    var categoryDialog by mutableStateOf(false)

    var subCategoryDialog by mutableStateOf(false)

    var workplaceDialog by mutableStateOf(false)

    var jobTypeDialog by mutableStateOf(false)

    var requirementsDialog by mutableStateOf(false)

    init {
        getCategories()
    }

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun onImageSelected(uri: Uri) {
        selectedImageUri = uri
    }


    fun updateCategory(category: Category) {
        _uiState.update {
            it.copy(categoryId = category.uid)
        }
    }

    fun updateSubCategory(subCategory: String) {
        _uiState.update {
            it.copy(subCategory = subCategory)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun updateLocation(location: String) {
        _uiState.update {
            it.copy(location = location)
        }
    }

    fun updateCompany(company: String) {
        _uiState.update {
            it.copy(company = company)
        }
    }

    fun updateWorkplace(workplace: Workplace) {
        _uiState.update {
            it.copy(workplace = workplace)
        }
    }

    fun updateJobType(jobType: JobType) {
        _uiState.update {
            it.copy(type = jobType)
        }
    }

    fun updateSalary(salary: String) {
        _uiState.update {
            it.copy(salary = salary)
        }
    }

    fun updateRequirements(requirements: List<String>) {
        _uiState.update {
            it.copy(requirements = requirements)
        }
    }

    fun postJob(navigateToJob: (String) -> Unit) {
        viewModelScope.launch {
            selectedImageUri?.let { uri ->
                val imageUrl = imageRepository.uploadImage(uri, "jobs")
                _uiState.update {
                    it.copy(image = imageUrl)
                }
            }
            val job = jobRepository.postJob(_uiState.value)
            navigateToJob(job.id)
        }
    }

    fun getCategoryName(categoryId: String): String {
        val category = _categories.value.firstOrNull { it.uid == categoryId }
        return category?.category ?: ""
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categories.value = categoryRepository.getCategories()
            println(categories)
        }
    }
}