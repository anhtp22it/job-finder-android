package com.tpanh.jobfinder.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.viewmodel.AddAJobViewModel

@Composable
fun AddJobTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close"
            )
        }
        Text(
            text = "Post",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFA500),
            fontSize = 18.sp
        )

    }
}

@Composable
fun AddJobContent(
    addajobViewModel: AddAJobViewModel = viewModel()
) {
    val uiState by addajobViewModel.JobInfor.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Add a job",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 3.dp, horizontal = 3.dp)
    ) {
        items(uiState) { job ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .width(350.dp)
                        .height(60.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(color = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.width(350.dp)
                    ) {
                        Text(
                            text = "${job.name}",
                            modifier = Modifier.padding(20.dp)
                                .width(160.dp)
                        )
                        Spacer(modifier = Modifier.width(100.dp))

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFA500).copy(0.3f), CircleShape)
                        )  {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color(0xFFFFA500),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun AddAJob(
    onBackClick: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            AddJobTopBar(
                onBackClick = { onBackClick() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AddJobContent()
        }
    }
}

@Preview
@Composable
fun AddJobPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AddAJob()
    }
}