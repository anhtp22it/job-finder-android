package com.tpanh.jobfinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobFilter
import com.tpanh.jobfinder.navigation.JobFinderScreen
import com.tpanh.jobfinder.screens.components.BottomAppBar
import com.tpanh.jobfinder.screens.components.FilterItem
import com.tpanh.jobfinder.screens.components.JobItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.SearchJobViewModel


@Composable
fun SearchJob(
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToSaveJob: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToPostJob: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToSpecialization: () -> Unit,
    navigateToJobDesc: (String) -> Unit,
    currentScreen: JobFinderScreen
) {
    Scaffold(
        topBar = {
            NavigateBackBar(
                navigateBack = { navigateBack() },
            )
        },
        bottomBar = {
            BottomAppBar(
                navigateToHome = { navigateToHome() },
                navigateToSaveJob = { navigateToSaveJob() },
                navigateToProfile = { navigateToProfile() },
                navigateToPostJob = { navigateToPostJob() },
                navigateToSearch = { navigateToSearch() },
                currentScreen = currentScreen
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            SearchJobContent(
                navigateToSpecialization = navigateToSpecialization,
                navigateToJobDesc = navigateToJobDesc
            )
        }
    }

}

@Composable
fun SearchJobContent(
    searchJobViewModel: SearchJobViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToSpecialization: () -> Unit,
    navigateToJobDesc: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp)),
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = searchJobViewModel.query,
                    onValueChange = {
                        searchJobViewModel.query = it
                        searchJobViewModel.searchJob()
                    },
                    placeholder = { Text("Design") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                        unfocusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    placeholder = { Text("California, USA") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Place,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                        unfocusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }


        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(8.dp)
                    .clickable {
                        navigateToSpecialization()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_slider),
                    contentDescription = "Slider",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            LazyRow(
                modifier = Modifier.fillMaxSize()
            ) {
                items(1) {
                    FilterItem("Senior designer")
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterItem("Designer")
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterItem("Full-time")
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val jobs by searchJobViewModel.jobs.collectAsState()
        jobs.forEach {
            val isSaved = searchJobViewModel.isSaved(it)
            JobItem(
                job = it,
                navigateToJobDesc = navigateToJobDesc,
                saveJob = {
                    if (isSaved) searchJobViewModel.unSaveJob(it) else searchJobViewModel.saveJob(it)
                },
                isJobSaved = isSaved
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

//@Preview
//@Composable
//fun SearchJobPreview() {
//    Surface {
//        SearchJob(
//            navigateBack = {},
//            navigateToHome = {},
//            navigateToSaveJob = {},
//            navigateToProfile = {},
//            navigateToPostJob = {},
//            navigateToSearch = {},
//            navigateToSpecialization = {},
//            navigateToJobDesc = {},
//            currentScreen = JobFinderScreen.SearchJob
//        )
//    }
//}
