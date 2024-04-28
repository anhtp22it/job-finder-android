package com.tpanh.jobfinder.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.viewmodel.ForgotPasswordViewModel

@Composable
fun ChangePassword(
    onBack: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            NavigateBackBar {
                onBack()
            }
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            ChangePasswordContent()
        }
    }
}

@Composable
fun ChangePasswordContent(
    forgotPasswordViewModel: ForgotPasswordViewModel = viewModel()
) {
    val uiState by forgotPasswordViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        Text(
            text = "Update Password",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Old Password",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            onValueChange = { forgotPasswordViewModel.onOldPasswordChanged(it) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            visualTransformation = if (forgotPasswordViewModel.oldPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = { Text(text = "•••••••••") },
            trailingIcon = {
                IconButton (
                    onClick = { forgotPasswordViewModel.toggleOldPasswordVisibility() }
                ) {

                    val visibilityIcon =
                        if (forgotPasswordViewModel.oldPasswordHidden) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                    val description = if (forgotPasswordViewModel.oldPasswordHidden) "Show password" else "Hide password"

                    Icon(imageVector = visibilityIcon, contentDescription = description)

                }
            }
        )
        Text(
            text = "New Password",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            onValueChange = { forgotPasswordViewModel.onNewPasswordChanged(it) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            visualTransformation = if (forgotPasswordViewModel.newPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = { Text(text = "•••••••••") },
            trailingIcon = {
                IconButton (
                    onClick = { forgotPasswordViewModel.toggleNewPasswordVisibility() }
                ) {

                    val visibilityIcon =
                        if (forgotPasswordViewModel.newPasswordHidden) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                    val description = if (forgotPasswordViewModel.newPasswordHidden) "Show password" else "Hide password"

                    Icon(imageVector = visibilityIcon, contentDescription = description)

                }
            }
        )
        Text(
            text = "Confirm Password",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            onValueChange = { forgotPasswordViewModel.onConfirmPasswordChanged(it) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            visualTransformation = if (forgotPasswordViewModel.confirmPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = { Text(text = "•••••••••") },
            trailingIcon = {
                IconButton (
                    onClick = { forgotPasswordViewModel.toggleConfirmPasswordVisibility() }
                ) {

                    val visibilityIcon =
                        if (forgotPasswordViewModel.confirmPasswordHidden) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                    val description = if (forgotPasswordViewModel.confirmPasswordHidden) "Show password" else "Hide password"

                    Icon(imageVector = visibilityIcon, contentDescription = description)

                }
            }
        )
    }
}

@Preview
@Composable
fun ChangePasswordPreview() {
    Surface {
        ChangePassword()
    }
}