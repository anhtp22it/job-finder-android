package com.tpanh.jobfinder.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.SpecializationViewModel

@Composable
fun Specialization(
    navigateBack: () -> Unit,
    navigateToFilter: () -> Unit,
    navigateToSearch: () -> Unit
) {
    Scaffold(
        topBar = {
            NavigateBackBar(
                navigateBack = { navigateBack() },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SpecializationContent(
                navigateToFilter = navigateToFilter,
                navigateToSearch = navigateToSearch
            )
        }
    }
}

@Composable
fun SpecializationContent(
    specializationViewModel: SpecializationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToFilter: () -> Unit,
    navigateToSearch: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = specializationViewModel.query,
                onValueChange = { specializationViewModel.onQueryChange(it) },
                label = { Text("Search") },
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
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxHeight()
            )

            Button(
                onClick = { navigateToFilter() },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA500)
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(50.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_setting),
                    contentDescription = "Setting"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Specialization",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

    val categories by specializationViewModel.categories.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(categories) { item ->
            CategoryComponent(
                icon = item.image,
                title = item.category,
                job = 0,
                onClick = {
                    navigateToSearch()
                }
            )
        }
    }
}

@Composable
fun CategoryComponent(
    icon: String,
    title: String,
    job: Int,
    onClick: () -> Unit
) {

    var isPressed by remember { mutableStateOf(false) }

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .size(width = 165.dp, height = 185.dp)
            .shadow(5.dp, shape = RoundedCornerShape(30.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { isPressed = !isPressed }
            )
            .scale(if (isPressed) 1.1f else 1f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color(0xFFFFA500),
                    containerColor = Color(0xFFFFA500).copy(alpha = 0.1f)
                ),
                modifier = Modifier
                    .size(70.dp)
            ) {
                AsyncImage(
                    model = icon,
                    contentDescription = title,
                    modifier = Modifier.size(40.dp),
                )
            }
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "${job} Jobs",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun SpecializationPreview() {
    Specialization(
        navigateBack = {},
        navigateToFilter = {},
        navigateToSearch = {}
    )
}



