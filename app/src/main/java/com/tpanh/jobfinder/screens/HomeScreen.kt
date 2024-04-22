package com.tpanh.jobfinder.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    onNavigateToOnBoarding: () -> Unit = {}
) {
    Text(text = "Home Screen")
    Button(onClick = { onNavigateToOnBoarding() }) {
        Text(text = "Next")
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}