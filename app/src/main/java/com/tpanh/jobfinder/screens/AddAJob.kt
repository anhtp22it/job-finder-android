package com.tpanh.jobfinder.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.Workplace
import com.tpanh.jobfinder.screens.components.CategoryRadioDialog
import com.tpanh.jobfinder.screens.components.InputDialog
import com.tpanh.jobfinder.screens.components.ListInputDialog
import com.tpanh.jobfinder.screens.components.NumberInputDialog
import com.tpanh.jobfinder.screens.components.RadioDialog
import com.tpanh.jobfinder.utils.normalizeString
import com.tpanh.jobfinder.viewmodel.PostJobViewModel

@Composable
fun AddJobTopBar(
    navigateToHome: () -> Unit,
    navigateToJob: () -> Unit,
    postJobViewModel: PostJobViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = { navigateToHome() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close"
            )
        }
        TextButton(onClick = { postJobViewModel.postJob(navigateToJob) }) {
            Text(
                text = "Post",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun AddJobContent(
    postJobViewModel: PostJobViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by postJobViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Add a job",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Job position",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.title.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.title,
                        fontSize = 14.sp
                    )
                }
            }
            if (uiState.title == null || uiState.title.isEmpty()) {
                IconButton(
                    onClick = { postJobViewModel.titleDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Title"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.titleDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Title"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = {
                    if (it != null) {
                        postJobViewModel.onImageSelected(it)
                    }
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (postJobViewModel.selectedImageUri != null) {
                    val painter = rememberImagePainter(
                        data = postJobViewModel.selectedImageUri,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    )
                    Image(
                        painter = painter,
                        contentDescription = "Job image",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Text(
                    text = "Job image",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            if (postJobViewModel.selectedImageUri == null) {
                IconButton(
                    onClick = { launcher.launch("image/*") },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Title"
                    )
                }
            } else {
                IconButton(
                    onClick = { launcher.launch("image/*") },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Title"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Job category",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.categoryId.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = postJobViewModel.getCategoryName(uiState.categoryId!!),
                        fontSize = 14.sp
                    )
                }
            }
            if (uiState.categoryId == null || uiState.categoryId.isEmpty()) {
                IconButton(
                    onClick = { postJobViewModel.categoryDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Category"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.categoryDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Category"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Job sub category",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.subCategory.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.subCategory,
                        fontSize = 14.sp
                    )
                }
            }
            if (uiState.subCategory == null || uiState.subCategory.isEmpty()) {
                IconButton(
                    onClick = { postJobViewModel.subCategoryDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Category"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.subCategoryDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Category"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Type of workplace",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.workplace != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = normalizeString(uiState.workplace!!.name),
                        fontSize = 14.sp
                    )
                }
            }

            if (uiState.workplace == null) {
                IconButton(
                    onClick = { postJobViewModel.workplaceDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Workplace"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.workplaceDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Workplace"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Job location",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.location.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.location,
                        fontSize = 14.sp
                    )
                }
            }
            if (uiState.location == null || uiState.location.isEmpty()) {
                IconButton(
                    onClick = { postJobViewModel.locationDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Location"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.locationDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Location"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Company",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.company.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.company,
                        fontSize = 14.sp
                    )
                }
            }
            if (uiState.company == null || uiState.company.isEmpty()) {
                IconButton(
                    onClick = { postJobViewModel.companyDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Company"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.companyDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Company"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Employment type",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.type != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = normalizeString(uiState.type!!.name),
                        fontSize = 14.sp
                    )
                }
            }
            if (uiState.type == null) {
                IconButton(
                    onClick = { postJobViewModel.jobTypeDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Job Type"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.jobTypeDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Job Type"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Salary",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = uiState.salary.toString(),
                    fontSize = 14.sp
                )
            }
            if (uiState.salary == 0) {
                IconButton(
                    onClick = { postJobViewModel.salaryDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Job Type"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.salaryDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Job Type"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Description",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    if (uiState.description.isEmpty()) {
                        IconButton(
                            onClick = { postJobViewModel.descDialog = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(
                                    0.1f
                                )
                            )
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Add Description"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { postJobViewModel.descDialog = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.edit),
                                contentDescription = "Edit Description"
                            )
                        }
                    }
                }
                if (uiState.type != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.description,
                        fontSize = 14.sp
                    )
                }
            }
            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.3f)
                )
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Title"
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = if (uiState.requirements.isEmpty()) Alignment.CenterVertically else Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    text = "Requirements",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (uiState.requirements.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    uiState.requirements.forEach {
                        Text(
                            text = "• $it",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
            if (uiState.requirements.isEmpty()) {
                IconButton(
                    onClick = { postJobViewModel.requirementsDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Job Type"
                    )
                }
            } else {
                IconButton(
                    onClick = { postJobViewModel.requirementsDialog = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Job Type"
                    )
                }
            }
        }
    }

    if (postJobViewModel.workplaceDialog) {
        RadioDialog(
            title = "Choose the type of workplace",
            description = "Decide and choose the type of place to work according to what you want ",
            options = Workplace.values().toList(),
            onOptionSelected = {
                postJobViewModel.updateWorkplace(it)
                postJobViewModel.workplaceDialog = false
            },
            onDismissRequest = { postJobViewModel.workplaceDialog = false },
            selectedOption = uiState.workplace
        )
    }

    if (postJobViewModel.jobTypeDialog) {
        RadioDialog(
            title = "Choose Job Type",
            description = "Determine and choose the type of work according to what you want",
            options = JobType.values().toList(),
            onOptionSelected = {
                postJobViewModel.updateJobType(it)
                postJobViewModel.jobTypeDialog = false
            },
            onDismissRequest = { postJobViewModel.jobTypeDialog = false },
            selectedOption = uiState.type
        )
    }

    if (postJobViewModel.titleDialog) {
        InputDialog(
            title = "Add a job title",
            value = uiState.title,
            onDismissRequest = { postJobViewModel.titleDialog = false },
            onConfirm = {
                postJobViewModel.updateTitle(it)
                postJobViewModel.titleDialog = false
            }
        )
    }

    if (postJobViewModel.categoryDialog) {
        val categories by postJobViewModel.categories.collectAsState()
        CategoryRadioDialog(
            title = "Choose job category",
            description = "Choose the category that best fits the job you're posting",
            options = categories,
            onOptionSelected = {
                postJobViewModel.updateSubCategory("")
                postJobViewModel.updateCategory(it)
                postJobViewModel.categoryDialog = false
            },
            onDismissRequest = { postJobViewModel.categoryDialog = false },
            selectedOption = postJobViewModel.categories.value.find { it.uid == uiState.categoryId }
        )
    }

    if (postJobViewModel.subCategoryDialog) {
        val category = postJobViewModel.categories.value.find { it.uid == uiState.categoryId }
        val subCategories = category?.subCategories ?: emptyList()
        RadioDialog(
            title = "Choose job sub category",
            description = "Choose the sub category that best fits the job you're posting",
            options = subCategories,
            onOptionSelected = {
                postJobViewModel.updateSubCategory(it)
                postJobViewModel.subCategoryDialog = false
            },
            onDismissRequest = { postJobViewModel.subCategoryDialog = false },
            selectedOption = uiState.subCategory
        )
    }

    if (postJobViewModel.locationDialog) {
        InputDialog(
            title = "Add a job location",
            value = uiState.location,
            onDismissRequest = { postJobViewModel.locationDialog = false },
            onConfirm = {
                postJobViewModel.updateLocation(it)
                postJobViewModel.locationDialog = false
            }
        )
    }

    if (postJobViewModel.salaryDialog) {
        NumberInputDialog(
            title = "Add a job salary",
            value = uiState.salary,
            onDismissRequest = { postJobViewModel.salaryDialog = false },
            onConfirm = {
                postJobViewModel.updateSalary(it)
                postJobViewModel.salaryDialog = false
            }
        )
    }

    if (postJobViewModel.descDialog) {
        InputDialog(
            title = "Add a job description",
            value = uiState.description,
            onDismissRequest = { postJobViewModel.descDialog = false },
            onConfirm = {
                postJobViewModel.updateDescription(it)
                postJobViewModel.descDialog = false
            }
        )
    }

    if (postJobViewModel.companyDialog) {
        InputDialog(
            title = "Add a company name",
            value = uiState.company,
            onDismissRequest = { postJobViewModel.companyDialog = false },
            onConfirm = {
                postJobViewModel.updateCompany(it)
                postJobViewModel.companyDialog = false
            }
        )
    }

    if (postJobViewModel.requirementsDialog) {
        ListInputDialog(
            title = "Add job requirements",
            initialList = uiState.requirements,
            onDismissRequest = { postJobViewModel.requirementsDialog = false },
            onConfirm = {
                postJobViewModel.updateRequirements(it)
                postJobViewModel.requirementsDialog = false
            }
        )
    }
}

@Composable
fun AddAJob(
    navigateToHome: () -> Unit,
    navigateToJob: (String) -> Unit,
    postJobViewModel: PostJobViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by postJobViewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            AddJobTopBar(
                navigateToHome = { navigateToHome() },
                navigateToJob = { navigateToJob(uiState.toString()) }
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
        AddAJob(
            navigateToHome = { },
            navigateToJob = { }
        )
    }
}