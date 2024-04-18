package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LanguageViewModel: ViewModel() {
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
        _myLanguages.value = _myLanguages.value.filterNot { it.id == language.id }
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
                Language(1, "English", "🇬🇧"),
                Language(2, "Vietnamese", "🇻🇳"),
                Language(3, "Japanese", "🇯🇵"),
            )
            _myLanguages.value = response
        }
    }

    private fun getAllLanguages() {
        viewModelScope.launch {
            val response = listOf(
                Language(1, mapOf("common" to "English"), "🇬🇧"),
                Language(2, mapOf("common" to "Vietnamese"), "🇻🇳"),
                Language(3, mapOf("common" to "Japanese"), "🇯🇵"),
                Language(4, mapOf("common" to "Chinese"), "🇨🇳"),
                Language(5, mapOf("common" to "Korean"), "🇰🇷"),
                Language(6, mapOf("common" to "French"), "🇫🇷"),
                Language(7, mapOf("common" to "German"), "🇩🇪"),
                Language(8, mapOf("common" to "Spanish"), "🇪🇸"),
                Language(9, mapOf("common" to "Italian"), "🇮🇹"),
                Language(10, mapOf("common" to "Russian"), "🇷🇺"),
                Language(11, mapOf("common" to "Portuguese"), "🇵🇹"),
                Language(12, mapOf("common" to "Arabic"), "🇸🇦"),
                Language(13, mapOf("common" to "Hindi"), "🇮🇳"),
                Language(14, mapOf("common" to "Bengali"), "🇧🇩"),
                Language(15, mapOf("common" to "Urdu"), "🇵🇰"),
                Language(16, mapOf("common" to "Turkish"), "🇹🇷"),
                Language(17, mapOf("common" to "Thai"), "🇹🇭"),
                Language(18, mapOf("common" to "Indonesian"), "🇮🇩"),
                Language(19, mapOf("common" to "Malay"), "🇲🇾"),
            )
            _allLanguages.value = response
            _searchResults.value = response
        }
    }
}