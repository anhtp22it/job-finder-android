package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.JobInfor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddAJobViewModel : ViewModel() {
    val JobInfor = MutableStateFlow<List<JobInfor>>(emptyList())

    init {
        getJobInfor()
    }
    private fun getJobInfor() {
        viewModelScope.launch {
            val response = listOf(
                JobInfor(mapOf("common" to "Job position*")),
                JobInfor(mapOf("common" to "Type of workplace")),
                JobInfor(mapOf("common" to "Administrative Assistant")),
                JobInfor(mapOf("common" to "Job location")),
                JobInfor(mapOf("common" to "Company")),
                JobInfor(mapOf("common" to "Employment type")),
                JobInfor(mapOf("common" to "Description")),
        )
            JobInfor.value = response
        }
    }
}