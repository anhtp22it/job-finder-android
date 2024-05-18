package com.tpanh.jobfinder.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.screens.components.MyJobItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.MyJobsViewModel

@Composable
fun MyJobs(
    navigateBack: () -> Unit,
    navigateToApplyJobDesc: (String) -> Unit
) {
    Scaffold(
        topBar = {
            NavigateBackBar {
                navigateBack()
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MyJobsContent(
                navigateToApplyJobDesc = navigateToApplyJobDesc
            )
        }
    }
}

@Composable
fun MyJobsContent(
    myJobsViewModel: MyJobsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToApplyJobDesc: (String) -> Unit
) {
    val myJobs by myJobsViewModel.myJobs.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "My Jobs",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        myJobs.forEach {
            MyJobItem(
                job = it,
                navigateToApplyJobDesc = navigateToApplyJobDesc
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun MyJobsPreview() {
    Surface {
        MyJobs(
            navigateBack = { },
            navigateToApplyJobDesc = { }
        )
    }
}