package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddSkillViewModel: ViewModel() {
    private val _mySkills = MutableStateFlow<User>(User())
    val mySkills = _mySkills.asStateFlow()

    private val _allSkills = MutableStateFlow<Set<String>>(emptySet())

    private val _searchResults = MutableStateFlow<Set<String>>(emptySet())
    val searchResults = _searchResults.asStateFlow()

    var search by mutableStateOf("")
        private set

    init {
        getMyLanguages()
        getAllLanguages()
        onSearchChange("")
    }

    fun deleteSkill(skill: String) {
        val currentUser = _mySkills.value
        currentUser.skills.minus(skill)
        _mySkills.value = currentUser
    }

    fun addSkill(skill: String) {
        val currentUser = _mySkills.value
        currentUser.skills.plus(skill)
        _mySkills.value = currentUser
    }

    fun onSearchChange(search: String) {
        if (search.isEmpty()) {
            _searchResults.value = emptySet()
        } else {
            this.search = search
            searchSkill(search)
        }
    }

    private fun searchSkill(skill: String) {
        viewModelScope.launch {
            _searchResults.value = _allSkills.value.filter { it.contains(skill, ignoreCase = true) }.toSet()
        }
    }

    private fun getMyLanguages() {
        viewModelScope.launch {
            val response = listOf(
                "Leadership",
                "Teamwork",
                "Visioner",
                "Target oriented",
                "Consistent",
                "Good communication skills",
                "English"
            )
            _mySkills.value.skills = response
        }
    }

    private fun getAllLanguages() {
        viewModelScope.launch {
            val response = setOf(
                "Leadership",
                "Teamwork",
                "Visioner",
                "Target oriented",
                "Consistent",
                "Good communication skills",
                "English",
                "Java",
                "Kotlin",
                "Swift",
                "Objective-C",
                "C++",
                "C#",
                "Python",
                "JavaScript",
                "HTML",
                "CSS",
                "SQL",
                "PHP",
                "Ruby",
                "Go",
                "Rust",
                "Scala",
                "TypeScript",
                "Dart",
                "Shell",
                "PowerShell",
                "Bash",
                "Perl",
                "Lua",
                "R",
                "Matlab",
            )
            _allSkills.value = response
            _searchResults.value = response
        }
    }
    
}