package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Language
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _myLanguages = MutableStateFlow<List<Language>>(emptyList())
    val myLanguages =  _myLanguages.asStateFlow()

    private val _allLanguages = MutableStateFlow<List<Language>>(emptyList())

    private val _searchResults = MutableStateFlow<List<Language>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    var search by mutableStateOf("")
        private set

    init {
        getMyLanguages()
        getAllLanguages()
    }

    fun deleteLanguage(language: Language) {
        _myLanguages.value = _myLanguages.value.filterNot { it == language }
    }

    fun addLanguage(language: Language) {
        _myLanguages.update { it + language }
    }

    fun onSearchChange(search: String) {
        this.search = search
        searchLanguage(search)
    }

    private fun searchLanguage(language: String) {
        viewModelScope.launch {
            _searchResults.value = _allLanguages.value.filter { it.name.contains(language, ignoreCase = true) }
        }
    }

    private fun getMyLanguages() {
        viewModelScope.launch {
            val response = listOf(
                Language("English", "🇬🇧"),
                Language("Vietnamese", "🇻🇳"),
                Language("Japanese", "🇯🇵"),
            )
            _myLanguages.value = response
        }
    }

    private fun getAllLanguages() {
        viewModelScope.launch {
            val response = listOf(
                Language(mapOf("common" to "English"), "🇬🇧"),
                Language(mapOf("common" to "Vietnamese"), "🇻🇳"),
                Language(mapOf("common" to "Japanese"), "🇯🇵"),
                Language(mapOf("common" to "Chinese"), "🇨🇳"),
                Language(mapOf("common" to "Korean"), "🇰🇷"),
                Language(mapOf("common" to "French"), "🇫🇷"),
                Language(mapOf("common" to "German"), "🇩🇪"),
                Language(mapOf("common" to "Spanish"), "🇪🇸"),
                Language(mapOf("common" to "Italian"), "🇮🇹"),
                Language(mapOf("common" to "Russian"), "🇷🇺"),
                Language(mapOf("common" to "Portuguese"), "🇵🇹"),
                Language(mapOf("common" to "Arabic"), "🇸🇦"),
                Language(mapOf("common" to "Hindi"), "🇮🇳"),
                Language(mapOf("common" to "Bengali"), "🇧🇩"),
                Language(mapOf("common" to "Urdu"), "🇵🇰"),
                Language(mapOf("common" to "Turkish"), "🇹🇷"),
                Language(mapOf("common" to "Thai"), "🇹🇭"),
                Language(mapOf("common" to "Indonesian"), "🇮🇩"),
                Language(mapOf("common" to "Malay"), "🇲🇾"),
            )
            _allLanguages.value = response
            _searchResults.value = response
        }
    }
}