package com.tpanh.jobfinder.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.screens.components.ApplyItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.ApplyJobDescriptionViewModel
import com.tpanh.jobfinder.viewmodel.JobDescriptionViewModel

@Composable
fun ApplyJobDesc(
    navigateBack: () -> Unit,
    jobId: String
) {
    Scaffold(
        topBar = {
            NavigateBackBar {
                navigateBack()
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ApplyJobDescContent(
                jobId = jobId
            )
        }
    }
}

@Composable
fun ApplyJobDescContent(
    applyJobDescriptionViewModel: ApplyJobDescriptionViewModel = viewModel(factory = AppViewModelProvider.Factory),
    jobId: String
) {
    applyJobDescriptionViewModel.getCandidates(jobId)
    val candidates by applyJobDescriptionViewModel.candidates.collectAsState()
    val users by applyJobDescriptionViewModel.users.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Candidates",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        candidates.forEach { jobApply ->
            ApplyItem(
                apply = jobApply,
                onAccept = { applyJobDescriptionViewModel.acceptApplication(jobApply.id, jobId) },
                onReject = { applyJobDescriptionViewModel.rejectApplication(jobApply.id, jobId) },
                user = users.find { user -> user.id == jobApply.userId } ?: User(),
            )
        }

    }
}