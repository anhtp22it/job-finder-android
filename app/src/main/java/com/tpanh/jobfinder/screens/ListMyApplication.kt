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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.screens.components.JobItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar

@Composable
fun ListMyApplication(
    navigateBack: () -> Unit
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
            ListMyApplicationContent()
        }
    }
}

@Composable
fun ListMyApplicationContent() {
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
        repeat(10) {
            JobItem(job = Job(), navigateToJobDesc = {}, saveJob = {}, isJobSaved = false)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewListMyApplication() {
    Surface {
        ListMyApplication(
            navigateBack = {}
        )
    }
}