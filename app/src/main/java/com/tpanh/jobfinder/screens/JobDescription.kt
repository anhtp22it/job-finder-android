package com.tpanh.jobfinder.screens

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.screens.components.ExpandableText
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.JobDescriptionViewModel

@Composable
fun JobDescription(
    navigateBack: () -> Unit,
    navigateToUploadCv: () -> Unit,
    jobId: String
) {
    Scaffold (
        topBar = {
            NavigateBackBar (
                navigateBack = { navigateBack() },
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            JobDescriptionContent(
                navigateToUploadCv = navigateToUploadCv,
                jobId = jobId
            )
        }
    }
}

@Composable
fun BulletPointText(text: String) {
    Text(
        "•   $text",
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}

@Composable
fun JobDescriptionContent(
    navigateToUploadCv: () -> Unit,
    jobId: String,
    jobDescriptionViewModel: JobDescriptionViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {

    val job by jobDescriptionViewModel.job.collectAsState()
    jobDescriptionViewModel.getJobById(jobId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = 25.dp)
                .zIndex(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            AsyncImage(
                model = job.image,
                contentDescription = "Logo",
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFAFECFE)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = job.title,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text (
                        text = job.company,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                    Text (
                        text = job.location,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                    Text(
                        text = DateUtils.getRelativeTimeSpanString(job.createdAt, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString(),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 18.sp
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Job Description",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            ExpandableText(
                text = job.description,

            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Requirements",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            job.requirements.forEach {
                Spacer(modifier = Modifier.height(12.dp))
                BulletPointText(it)
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    "APPLY NOW",
                    letterSpacing = 2.sp,
                )
            }

        }
    }
}


@Preview
@Composable
fun JobDescriptionPreview() {
    Surface {
        JobDescription(
            navigateBack = {},
            navigateToUploadCv = {},
            jobId = "7m6d7h4hw5OAcTrDODpl"
        )
    }
}

