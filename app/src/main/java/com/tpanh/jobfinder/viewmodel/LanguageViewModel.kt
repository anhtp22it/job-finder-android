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

    private val _allLanguages = MutableStateFlow<Set<Language>>(emptySet())

    private val _searchResults = MutableStateFlow<Set<Language>>(emptySet())
    val searchResults = _searchResults.asStateFlow()

    var search by mutableStateOf("")
        private set

    init {
        getMyLanguages()
        getAllLanguages()
    }

    fun deleteLanguage(language: Language) {
        _myLanguages.value = _myLanguages.value.filterNot { it == language }
        viewModelScope.launch {
            userRepository.updateLanguages(_myLanguages.value)
        }
        getMyLanguages()
    }

    fun addLanguage(language: Language, navigateToLanguageScreen: () -> Unit) {
        viewModelScope.launch {
            _myLanguages.update { it + language }
            userRepository.updateLanguages(_myLanguages.value)
        }
        navigateToLanguageScreen()
    }

    fun onSearchChange(search: String) {
        this.search = search
        searchLanguage(search)
    }

    private fun searchLanguage(language: String) {
        viewModelScope.launch {
            _searchResults.value =
                _allLanguages.value.filter { it.name.contains(language, ignoreCase = true) }.toSet()
        }
    }

    private fun getMyLanguages() {
        viewModelScope.launch {
            val response = userRepository.getCurrentUser().languages
            _myLanguages.value = response
        }
    }

    private fun getAllLanguages() {
        viewModelScope.launch {
            val response = setOf(
                Language("English", "🇬🇧"),
                Language("Vietnamese", "🇻🇳"),
                Language("Japanese", "🇯🇵"),
                Language("Chinese", "🇨🇳"),
                Language("Korean", "🇰🇷"),
                Language("French", "🇫🇷"),
                Language("German", "🇩🇪"),
                Language("Spanish", "🇪🇸"),
                Language("Italian", "🇮🇹"),
                Language("Russian", "🇷🇺"),
                Language("Portuguese", "🇵🇹"),
                Language("Arabic", "🇸🇦"),
                Language("Hindi", "🇮🇳"),
                Language("Bengali", "🇧🇩"),
                Language("Urdu", "🇵🇰"),
                Language("Turkish", "🇹🇷"),
                Language("Thai", "🇹🇭"),
                Language("Indonesian", "🇮🇩"),
                Language("Malay", "🇲🇾"),
                Language("Filipino", "🇵🇭"),
                Language("Vietnamese", "🇻🇳"),
            )
            _allLanguages.value = response
            _searchResults.value = response
        }
    }
}