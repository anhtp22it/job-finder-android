package com.tpanh.jobfinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.navigation.JobFinderScreen
import com.tpanh.jobfinder.screens.components.BottomAppBar
import com.tpanh.jobfinder.screens.components.JobItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar


@Composable
fun SearchJob(
    navigateBack: () -> Unit,
    navigateToUploadCv: (Any?) -> Unit,
    navigateToHome: () -> Unit = {},
    navigateToSaveJob: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
    navigateToPostJob: () -> Unit = {},
    navigateToSearch: () -> Unit = {},
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
            SearchJobContent()
        }
    }

}




@Composable
fun SearchJobContent() {
    var buttonColor1 by remember { mutableStateOf(Color(0xFF9AA0AC)) }
    var buttonColor2 by remember { mutableStateOf(Color(0xFF9AA0AC)) }
    var buttonColor3 by remember { mutableStateOf(Color(0xFF9AA0AC)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp)),
                contentScale = ContentScale.Crop
            )


            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Search job") },
                leadingIcon = {
                              Icon(
                                  imageVector = Icons.Filled.Search,
                                  contentDescription = "Search"
                              )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.Black.copy(alpha = 0.4f),
                    unfocusedLabelColor = Color.Black.copy(alpha = 0.4f),
                    focusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                    unfocusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(width = 300.dp, height = 70.dp)
                    .align(Alignment.Center)
                    .padding(16.dp)

            )


            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Location") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = "Search"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.Black.copy(alpha = 0.4f),
                    unfocusedLabelColor = Color.Black.copy(alpha = 0.4f),
                    focusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                    unfocusedLeadingIconColor = Color.Black.copy(alpha = 0.4f),
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(width = 300.dp, height = 70.dp)
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }


        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        ) {
            Button(
                onClick = { buttonColor1 = if (buttonColor1 == Color(0xFF9AA0AC)) Color(0xFF082B5F) else Color(0xFF9AA0AC) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor1
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(width = 120.dp, height = 40.dp)
            ) {
                Text("Senior designer")
            }

            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = { buttonColor2 = if (buttonColor2 == Color(0xFF9AA0AC)) Color(0xFF082B5F) else Color(0xFF9AA0AC) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor2
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(width = 100.dp, height = 40.dp)
            ) {
                Text("Designer")
            }

            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = { buttonColor3 = if (buttonColor3 == Color(0xFF9AA0AC)) Color(0xFF082B5F) else Color(0xFF9AA0AC) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor3
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(width = 100.dp, height = 40.dp)
            ) {
                Text("Full time")
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
        repeat(2) {
            JobItem(job = Job(), navigateToJobDesc = {}, saveJob = {}, isJobSaved = false)
            Spacer(modifier = Modifier.height(16.dp))
        }


    }
}

@Preview
@Composable
fun SearchJobPreview() {
    Surface {
        SearchJob(
            navigateBack = {},
            navigateToUploadCv = {},
            currentScreen = JobFinderScreen.SearchJob
        )
    }
}
