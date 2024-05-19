package com.tpanh.jobfinder.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.JobApply
import com.tpanh.jobfinder.repository.ImageRepository
import com.tpanh.jobfinder.repository.JobApplyRepository
import com.tpanh.jobfinder.repository.UserRepository
import com.tpanh.jobfinder.utils.getFileName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyApplicationViewModel(
    private val jobApplyRepository: JobApplyRepository,
    private val imageRepository: ImageRepository
): ViewModel() {
    private val _apply = MutableStateFlow(JobApply())
    val apply = _apply.asStateFlow()

    var pdfUri by  mutableStateOf<Uri?>(null)

    fun getMyApplies(applyId: String) {
        viewModelScope.launch {
            val apply = jobApplyRepository.getApplyById(applyId)
            _apply.value = apply

            pdfUri = Uri.parse(apply.cv)
        }
    }
}
