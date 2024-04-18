package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Language
import com.tpanh.jobfinder.model.Skill
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddSkillViewModel: ViewModel() {
    private val _mySkills = MutableStateFlow<Set<Skill>>(emptySet())
    val mySkills = _mySkills.asStateFlow()

    private val _allSkills = MutableStateFlow<List<Skill>>(emptyList())

    private val _searchResults = MutableStateFlow<List<Skill>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    var search by mutableStateOf("")
        private set

    init {
        getMyLanguages()
        getAllLanguages()
        onSearchChange("")
    }

    fun deleteSkill(skill: Skill) {
        _mySkills.update { it - skill }
    }

    fun addSkill(skill: Skill) {
        _mySkills.update { it + skill }
    }

    fun onSearchChange(search: String) {
        if (search.isEmpty()) {
            _searchResults.value = emptyList()
        } else {
            this.search = search
            searchSkill(search)
        }
    }

    private fun searchSkill(skill: String) {
        viewModelScope.launch {
            _searchResults.value = _allSkills.value.filter { it.name.contains(skill, ignoreCase = true) }
        }
    }

    private fun getMyLanguages() {
        viewModelScope.launch {
            val response = setOf(
                Skill(1, "Leadership"),
                Skill(2, "Teamwork"),
                Skill(3, "Visioner"),
                Skill(3, "Target oriented"),
                Skill(4, "Consistent"),
                Skill(5, "Good communication skills"),
                Skill(6, "English")
            )
            _mySkills.value = response
        }
    }

    private fun getAllLanguages() {
        viewModelScope.launch {
            val response = listOf(
                Skill(1, "Leadership"),
                Skill(2, "Teamwork"),
                Skill(3, "Visioner"),
                Skill(3, "Target oriented"),
                Skill(4, "Consistent"),
                Skill(5, "Good communication skills"),
                Skill(6, "English"),
                Skill(7, "Java"),
                Skill(8, "Kotlin"),
                Skill(9, "Swift"),
                Skill(10, "Objective-C"),
                Skill(11, "C++"),
                Skill(12, "C#"),
                Skill(13, "Python"),
                Skill(14, "JavaScript"),
                Skill(15, "HTML"),
                Skill(16, "CSS"),
                Skill(17, "SQL"),
                Skill(18, "PHP"),
                Skill(19, "Ruby"),
                Skill(20, "Go"),
                Skill(21, "Rust"),
                Skill(22, "Scala"),
                Skill(23, "TypeScript"),
                Skill(24, "Dart"),
                Skill(25, "Shell"),
                Skill(26, "PowerShell"),
                Skill(27, "Bash"),
                Skill(28, "Perl"),
                Skill(29, "Lua"),
                Skill(30, "R"),
                Skill(31, "Matlab"),
            )
            _allSkills.value = response
            _searchResults.value = response
        }
    }
}