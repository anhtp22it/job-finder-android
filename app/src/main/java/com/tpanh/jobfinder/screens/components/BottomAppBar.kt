package com.tpanh.jobfinder.screens.components

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.tpanh.jobfinder.navigation.JobFinderScreen

@Composable
fun BottomAppBar(
    navigateToHome: () -> Unit,
    navigateToSaveJob: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToPostJob: () -> Unit,
    navigateToSearch: () -> Unit,
    currentScreen: JobFinderScreen
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        IconButton(onClick = { navigateToHome() }) {
            Icon(
                Icons.Outlined.Home,
                contentDescription = "Home",
                tint = if (currentScreen == JobFinderScreen.Home) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                }
            )
        }
        IconButton(onClick = { navigateToSearch() }) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Setting",
                tint = if (currentScreen == JobFinderScreen.SearchJob) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                }
            )
        }
        IconButton(
            onClick = { navigateToPostJob() },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = Color.White
            )
        ) {
            Icon(
                Icons.Outlined.Add,
                contentDescription = "Home"
            )
        }
        IconButton(onClick = { navigateToSaveJob() }) {
            Icon(
                Icons.Outlined.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = if (currentScreen == JobFinderScreen.SaveJob) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                }
            )
        }
        IconButton(onClick = { navigateToProfile() }) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = "Profile",
                tint = if (currentScreen == JobFinderScreen.ViewProfile) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomAppBarPreview() {
    BottomAppBar(
        navigateToHome = {},
        navigateToSaveJob = {},
        navigateToProfile = {},
        navigateToPostJob = {},
        navigateToSearch = {},
        currentScreen = JobFinderScreen.Home
    )
}