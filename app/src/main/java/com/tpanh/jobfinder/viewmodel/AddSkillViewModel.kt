package com.tpanh.jobfinder.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddSkillViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _mySkills = MutableStateFlow<Set<String>>(emptySet())
    val mySkills = _mySkills.asStateFlow()

    private val _allSkills = MutableStateFlow<Set<String>>(emptySet())

    private val _searchResults = MutableStateFlow<Set<String>>(emptySet())
    val searchResults = _searchResults.asStateFlow()

    var search by mutableStateOf("")
        private set

    init {
        getMySkills()
        getAllSkills()
        onSearchChange("")
    }

    fun deleteSkill(skill: String) {
        val currentSkill = _mySkills.value.toMutableSet()
        currentSkill.remove(skill)
        _mySkills.value = currentSkill
    }

    fun addSkill(skill: String) {
        val currentSkill = _mySkills.value.toMutableSet()
        currentSkill.add(skill)
        _mySkills.value = currentSkill
        Log.d("AddSkillViewModel", "addSkill: ${_mySkills.value}")
    }

    fun onSearchChange(search: String) {
        this.search = search
        searchSkill()
    }

    fun saveSkills(navigateToViewProfile: () -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getCurrentUser()
            val updatedUser = user.copy(skills = mySkills.value.toList())
            userRepository.updateUser(updatedUser)
        }
        navigateToViewProfile()
    }

    private fun searchSkill() {
        if (search.isEmpty()) {
            _searchResults.value = emptySet()
        } else {
            val results = _allSkills.value.filter { it.contains(search, ignoreCase = true) }
            _searchResults.value = results.toSet()
        }
    }

    private fun getMySkills() {
        viewModelScope.launch {
            val response = userRepository.getCurrentUser().skills.toSet()
            _mySkills.value = response
        }
    }

    private fun getAllSkills() {
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