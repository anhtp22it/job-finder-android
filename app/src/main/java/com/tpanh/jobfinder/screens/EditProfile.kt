package com.tpanh.jobfinder.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Class
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.viewmodel.EditProfileViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    editProfileViewModel: EditProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToMyApplications: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToViewProfile: () -> Unit
) {

    val user by editProfileViewModel.uiState.collectAsState()

    val format = SimpleDateFormat("dd MMM yyyy")

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            if (it != null) {
                editProfileViewModel.onImageSelected(it)
            }
        }
    )

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
                    if (editProfileViewModel.selectedImageUri != null) {
                        val painter = rememberImagePainter(
                            data = editProfileViewModel.selectedImageUri,
                            builder = {
                                transformations(CircleCropTransformation())
                            }
                        )
                        Image(
                            painter = painter,
                            contentDescription = "Job image",
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    } else {
                        AsyncImage(
                            model = user.avatar,
                            contentDescription = "Job image",
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = user.fullName,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = user.location,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { launcher.launch("image/*") },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent.copy(alpha = 0.2f),
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Change image",
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )
                    }

                }
                Row {
                    IconButton(onClick = { editProfileViewModel.logout(navigateToLogin) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_reply_24),
                            contentDescription = "Logout",
                            tint = Color.White,
                            modifier = Modifier.rotate(180f)
                        )
                    }

                    IconButton(onClick = { navigateToMyApplications() }) {
                        Icon(
                            Icons.Outlined.Class,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Fullname",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = user.fullName,
                onValueChange = { editProfileViewModel.updateFullName(it) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Date of Birth",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { editProfileViewModel.isDateOfBirthPicker = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = format.format(Date(user.dateOfBirth)),
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Normal
                )
            }
            if (editProfileViewModel.isDateOfBirthPicker) {
                val datePickerState = rememberDatePickerState()
                val confirmEnable = derivedStateOf { datePickerState.selectedDateMillis != null }

                DatePickerDialog(
                    onDismissRequest = { editProfileViewModel.isDateOfBirthPicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                editProfileViewModel.isDateOfBirthPicker = false
                                var date = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                                editProfileViewModel.updateDateOfBirth(date)
                            },
                            enabled = confirmEnable.value
                        ) {
                            Text("Ok")
                        }
                    }) {
                    DatePicker(state = datePickerState)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Gender",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = user.gender == 1, onClick = {  editProfileViewModel.updateGender(1) })
                    Text(
                        text = "Male",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = user.gender == 0, onClick = { editProfileViewModel.updateGender(0) })
                    Text(
                        text = "Female",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Email Address",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = user.email,
                onValueChange = { editProfileViewModel.updateEmail(it) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Phone Number",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = user.phoneNumber,
                onValueChange = { editProfileViewModel.updatePhoneNumber(it) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Location",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = user.location,
                onValueChange = { editProfileViewModel.updateLocation(it) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(horizontal = 70.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        editProfileViewModel.updateUser()
                        Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(
                        text = "SAVE",
                        letterSpacing = 2.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun EditProfilePreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        EditProfile(
            navigateToMyApplications = {  },
            navigateToViewProfile = {  },
            navigateToLogin = {  }
        )
    }
}