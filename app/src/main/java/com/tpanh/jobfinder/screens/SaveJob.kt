package com.tpanh.jobfinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.navigation.JobFinderScreen
import com.tpanh.jobfinder.viewmodel.SaveJobViewModel

@Composable
fun SaveJob(
    saveJobViewModel: SaveJobViewModel = viewModel(),
    navigateToHome: () -> Unit,
    navigateToSaveJob: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToPostJob: () -> Unit,
    navigateToSearch: () -> Unit,
    currentScreen: JobFinderScreen
) {
    val uiState by saveJobViewModel.uiState.collectAsState()
    if (uiState.isEmpty()) {
        NoJobSave(
            navigateToSearchJob = {}
        )
    } else {
        Scaffold (
            bottomBar = {
                com.tpanh.jobfinder.screens.components.BottomAppBar(
                    navigateToHome = { navigateToHome() },
                    navigateToSaveJob = { navigateToSaveJob() },
                    navigateToProfile = { navigateToProfile() },
                    navigateToPostJob = { navigateToPostJob() },
                    navigateToSearch = { navigateToSearch() },
                    currentScreen = currentScreen
                )
            }
        ) {
            Column (
                modifier = Modifier.padding(it)
            ) {
                SaveJobContent(
                    saveJobViewModel = saveJobViewModel
                )
            }
        }
    }
}

@Composable
fun SaveJobContent(
    saveJobViewModel: SaveJobViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saved Jobs",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        saveJobViewModel.uiState.value.forEach { job ->
            com.tpanh.jobfinder.screens.components.SaveJobItem(
                openMoreOption = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    OptionDialog(
        onApplyJob = {},
        onDeleteJobSaved = {}
    )
}

@Composable
fun OptionDialog(
    onApplyJob: () -> Unit,
    onDeleteJobSaved: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ },
        title = {
            Divider(
                modifier = Modifier
                    .height(5.dp)
                    .padding(horizontal = 96.dp),
                color = Color.DarkGray,
            )
        },
        text = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Delete, contentDescription = "Delete")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Delete")
                    }
                }
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.CheckCircleOutline, contentDescription = "Apply")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Apply")
                    }
                }
            }
        }
    )
}

@Composable
fun NoJobSave(
    navigateToSearchJob: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Savings",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "You don't have any jobs saved, please find it in search to save jobs",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(horizontal = 72.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Image(
            painter = painterResource(id = R.drawable.img_save),
            contentDescription = "No job saved",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { navigateToSearchJob() }
        ) {
            Text(
                text = "FIND A JOB",
                letterSpacing = 1.sp,
            )
        }
    }
}

@Preview
@Composable
fun SaveJobPreview() {
    Surface {
        SaveJob(
            currentScreen = JobFinderScreen.SaveJob,
            navigateToHome = {},
            navigateToSaveJob = {},
            navigateToProfile = {},
            navigateToPostJob = {},
            navigateToSearch = {}
        )
    }
}