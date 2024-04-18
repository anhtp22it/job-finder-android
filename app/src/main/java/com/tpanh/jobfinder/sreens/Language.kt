package com.tpanh.jobfinder.sreens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.model.Language
import com.tpanh.jobfinder.viewmodel.LanguageViewModel

@Composable
fun LanguageTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@Composable
fun LanguageContent(
    navigateToAddLanguageScreen: () -> Unit,
    languageViewModel: LanguageViewModel = viewModel()
) {
    val uiState by languageViewModel.myLanguages.collectAsState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Language",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )

        Button(
            onClick = { navigateToAddLanguageScreen() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                text = "Add",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.6f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box (
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 32.dp)
    ) {
        items(uiState) { language ->
            LanguageItem(
                language,
                deleteItem = {
                    languageViewModel.deleteLanguage(language)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun LanguageItem(
    language: Language,
    deleteItem: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "${language.flag}",
                fontSize = 32.sp,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${language.name}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        IconButton(onClick = { deleteItem() }) {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = "Delete",
                tint = Color.Red
            )
        }
    }
}

@Composable
fun LanguageScreen(
    navigateToAddLanguageScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            LanguageTopBar()
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LanguageContent(
                navigateToAddLanguageScreen = navigateToAddLanguageScreen
            )
        }
    }
}

@Preview
@Composable
fun LanguageScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LanguageScreen(
            navigateToAddLanguageScreen = { }
        )
    }
}