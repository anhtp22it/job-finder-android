package com.tpanh.jobfinder.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.screens.components.ApplyJobItem
import com.tpanh.jobfinder.screens.components.JobItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.ListMyApplicationViewModel
import com.tpanh.jobfinder.viewmodel.MyApplicationViewModel

@Composable
fun ListMyApplication(
    navigateBack: () -> Unit,
    navigateToApplyJobDesc: (String) -> Unit
) {
    Scaffold (
        topBar = {
            NavigateBackBar (
                navigateBack = { navigateBack() }
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            ListMyApplicationContent(
                navigateToApplyJobDesc = navigateToApplyJobDesc
            )
        }
    }
}

@Composable
fun ListMyApplicationContent(
    listMyApplicationViewModel: ListMyApplicationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToApplyJobDesc: (String) -> Unit
) {

    val appliesList by listMyApplicationViewModel.myApplies.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your application",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        appliesList.forEach { apply ->
            val isSaved = listMyApplicationViewModel.isSaved(apply.job)
            ApplyJobItem(
                apply = apply,
                navigateToApplyJobDesc = { navigateToApplyJobDesc(apply.id) },
                saveJob = {
                    if (isSaved) listMyApplicationViewModel.unSaveJob(it) else listMyApplicationViewModel.saveJob(it)
                },
                isJobSaved = false
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewListMyApplication() {
    Surface {
        ListMyApplication(
            navigateBack = {},
            navigateToApplyJobDesc = {}
        )
    }
}