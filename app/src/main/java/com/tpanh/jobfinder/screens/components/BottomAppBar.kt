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
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomAppBar(
    navigateToHome: () -> Unit = {},
    navigateToSaveJob: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
    navigateToPostJob: () -> Unit = {},
    navigateToSetting: () -> Unit = {},
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
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        IconButton(onClick = { navigateToSaveJob() }) {
            Icon(
                Icons.Outlined.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = MaterialTheme.colorScheme.surfaceContainer
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
                contentDescription = "Home",
                tint = MaterialTheme.colorScheme.surfaceContainer
            )
        }
        IconButton(onClick = { navigateToProfile() }) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = "Profile",
                tint = MaterialTheme.colorScheme.surfaceContainer
            )
        }
        IconButton(onClick = { navigateToSetting() }) {
            Icon(
                Icons.Outlined.Settings,
                contentDescription = "Setting",
                tint = MaterialTheme.colorScheme.surfaceContainer
            )
        }
    }
}

@Preview
@Composable
fun BottomAppBarPreview() {
    BottomAppBar()
}