package com.tpanh.jobfinder.sreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.tpanh.jobfinder.viewmodel.AddSkillViewModel

@Composable
fun AddSkillTopBar(
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
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddSkillContent(
    addSkillViewModel: AddSkillViewModel = viewModel()
) {
    val mySkill by addSkillViewModel.mySkills.collectAsState()
    val searchSkill by addSkillViewModel.searchResults.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Add Skill",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            value = addSkillViewModel.search,
            onValueChange = {
                addSkillViewModel.onSearchChange(it)
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            placeholder = { Text("Search skills", fontSize = 12.sp) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchSkill.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            addSkillViewModel.onSearchChange("")
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
        if (searchSkill.isEmpty()) {
            FlowRow() {
//                mySkill.forEach { skill ->
//                    SkillItem(
//                        skill.name,
//                        onClick = {
//                            addSkillViewModel.deleteSkill(skill)
//                        }
//                    )
//                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "SAVE",
                    letterSpacing = 2.sp,
                )
            }
        } else {
            Column {
                searchSkill.forEach { skill  ->
//                    Text(
//                        text =skill.name,
//                        color = MaterialTheme.colorScheme.onPrimaryContainer,
//                        fontSize = 14.sp,
//                        modifier = Modifier.clickable {
//                            addSkillViewModel.addSkill(skill)
//                            addSkillViewModel.onSearchChange("")
//                        }
//                    )
                    Spacer(modifier =  Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun SkillItem(
    s: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = s,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 12.sp
            )
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp),
                onClick = { onClick() }
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Delete",
                )
            }
        }
    }
}

@Composable
fun AddSkill(
    onBackClick: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            AddSkillTopBar(
                onBackClick = { onBackClick() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AddSkillContent()
        }
    }
}

@Preview
@Composable
fun AddSkillPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AddSkill()
    }
}