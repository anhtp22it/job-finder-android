package com.tpanh.jobfinder.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.model.ApplicationStatus
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.screens.components.ApplyItem
import com.tpanh.jobfinder.screens.components.ChoosingItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.ui.theme.Shapes
import com.tpanh.jobfinder.utils.normalizeString
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
        LazyRow (
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            item {
                ChoosingItem(
                    selected = applyJobDescriptionViewModel.currentStatus == "ALL",
                    status = "ALL",
                    onClick = { applyJobDescriptionViewModel.currentStatus = "ALL"}
                )
            }
            items(ApplicationStatus.values().toList()) { status ->
                ChoosingItem(
                    status = status.name,
                    selected = applyJobDescriptionViewModel.currentStatus == status.name,
                    onClick = { applyJobDescriptionViewModel.currentStatus = status.name }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(
                if (applyJobDescriptionViewModel.currentStatus == "ALL") {
                    candidates
                } else {
                    candidates.filter { it.status == ApplicationStatus.valueOf(applyJobDescriptionViewModel.currentStatus) }
                }
            ) { jobApply ->
                ApplyItem(
                    apply = jobApply,
                    onAccept = { applyJobDescriptionViewModel.acceptApplication(jobApply.id, jobId) },
                    onReject = { applyJobDescriptionViewModel.rejectApplication(jobApply.id, jobId) },
                    user = users.find { user -> user.id == jobApply.userId } ?: User(),
                )
            }
        }
    }
}