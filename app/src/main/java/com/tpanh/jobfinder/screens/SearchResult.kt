package com.tpanh.jobfinder.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.screens.components.JobItem
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.SearchViewModel

@Composable
fun SearchResult(
    navigateBack: () -> Unit
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
            SearchResultContent()
        }
    }
}

@Composable
fun SearchResultContent(
    searchViewModel: SearchViewModel = viewModel()
) {

    val uiState = searchViewModel.uiState.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            value = searchViewModel.search,
            onValueChange = {
                searchViewModel.onSearchChange(it)
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            placeholder = { Text("Design", fontSize = 12.sp) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchViewModel.search.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            searchViewModel.onSearchChange("")
                        }
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Clear"
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        repeat(10) {
            JobItem()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun SearchNoResultPreview() {
    Surface {
        SearchResult(
            navigateBack = {}
        )
    }
}