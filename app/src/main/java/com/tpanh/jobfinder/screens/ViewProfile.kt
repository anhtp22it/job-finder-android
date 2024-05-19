package com.tpanh.jobfinder.screens

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
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Cases
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.navigation.JobFinderScreen
import com.tpanh.jobfinder.screens.components.BottomAppBar
import com.tpanh.jobfinder.viewmodel.ViewProfileViewModel

@Composable
fun ViewProfile(
    currentScreen: JobFinderScreen,
    navigateToSearch: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToSaveJob: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToPostJob: () -> Unit,
    navigateToAboutMe: () -> Unit,
    navigateToWorkExperience: () -> Unit,
    navigateToEducation: () -> Unit,
    navigateToSkill: () -> Unit,
    navigateToLanguage: () -> Unit
) {
    Scaffold (
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
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ViewProfileContent(
                navigateToSearch = navigateToSearch,
                navigateToEditProfile = navigateToEditProfile,
                navigateToAboutMe = navigateToAboutMe,
                navigateToWorkExperience = navigateToWorkExperience,
                navigateToEducation = navigateToEducation,
                navigateToSkill = navigateToSkill,
                navigateToLanguage = navigateToLanguage
            )
        }
    }
}

@Composable
fun ViewProfileContent(
    viewProfileViewModel: ViewProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToSearch: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToAboutMe: () -> Unit,
    navigateToWorkExperience: () -> Unit,
    navigateToEducation: () -> Unit,
    navigateToSkill: () -> Unit,
    navigateToLanguage: () -> Unit,
) {

    val uiState by viewProfileViewModel.uiState.collectAsState()

    val user by viewProfileViewModel.uiState.collectAsState()
    viewProfileViewModel.getCurrentUser()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_background),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
//                    Image(
//                        bitmap = scaledBitmap.asImageBitmap(),
//                        contentDescription = "avatar",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .width(60.dp)
//                            .height(60.dp)
//                            .clip(CircleShape)
//                            .background(Color.White)
//                    )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(uiState.avatar)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = uiState.fullName,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = uiState.location,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { navigateToEditProfile() },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent.copy(alpha = 0.2f),
                                contentColor = Color.White
                            ),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Edit profile",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    Icons.Outlined.Edit,
                                    contentDescription = "Edit profile",
                                )
                            }
                        }
                    }

                }
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Outlined.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = { navigateToSearch() }) {
                        Icon(
                            Icons.Outlined.Settings,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.AccountCircle,
                        contentDescription = "profile",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "About me",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { navigateToAboutMe() },
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.Cases,
                        contentDescription = "work experience",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Work experience",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { navigateToWorkExperience() },
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_graduation_cap),
                        contentDescription = "education",
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Education",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { navigateToEducation() },
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.AcUnit,
                        contentDescription = "skill",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Skill",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { navigateToSkill() },
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Language,
                        contentDescription = "language",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Language",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { navigateToLanguage() },
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

            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewViewProfile() {
    Surface {
        ViewProfile(
            navigateToSearch = {},
            currentScreen = JobFinderScreen.EditProfile,
            navigateToEditProfile = {},
            navigateToHome = {},
            navigateToSaveJob = {},
            navigateToProfile = {},
            navigateToPostJob = {},
            navigateToAboutMe = {},
            navigateToWorkExperience = {},
            navigateToEducation = {},
            navigateToSkill = {},
            navigateToLanguage = {}
        )
    }
}