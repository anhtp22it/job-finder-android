package com.tpanh.jobfinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.di.AppViewModelProvider
import com.tpanh.jobfinder.viewmodel.LoginViewModel

@Composable
fun Login(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToHome: () -> Unit,
    loginViewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val uiState by loginViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Lorem impsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
        Spacer(modifier = Modifier.height(72.dp))
        Text(
            text = "Email",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.email,
            onValueChange = { loginViewModel.updateEmail(it) },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Brandonelouis@gmail.com",
                    fontSize = 12.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.87f),
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Password",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            onValueChange = { loginViewModel.updatePassword(it) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            visualTransformation = if (loginViewModel.passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = { Text(text = "•••••••••") },
            trailingIcon = {
                IconButton (
                    onClick = { loginViewModel.togglePasswordVisibility() }
                ) {

                    val visibilityIcon =
                        if (loginViewModel.passwordHidden) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                    val description = if (loginViewModel.passwordHidden) "Show password" else "Hide password"

                    Icon(imageVector = visibilityIcon, contentDescription = description)

                }
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = loginViewModel.rememberMe,
                    onCheckedChange = { loginViewModel.updateRememberMe(it) },
                    colors = CheckboxColors(
                        checkedCheckmarkColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        uncheckedCheckmarkColor = Color.Transparent,
                        checkedBoxColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedBoxColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledCheckedBoxColor = Color.Transparent,
                        disabledUncheckedBoxColor = Color.Transparent,
                        disabledIndeterminateBoxColor = Color.Transparent,
                        checkedBorderColor = Color.Transparent,
                        uncheckedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        disabledUncheckedBorderColor = Color.Transparent,
                        disabledIndeterminateBorderColor = Color.Transparent
                    )
                )
                Text(
                    text = "Remember me",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            TextButton(onClick = { navigateToForgotPassword() }) {
                Text(text = "Forgot Password?")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { loginViewModel.login(uiState.email, uiState.password, navigateToHome) }
        ) {
            Text(
                text = "LOGIN",
                letterSpacing = 2.sp,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { /*TODO*/ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Login With Google",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "SIGN IN WITH GOOGLE",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account yet?",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            TextButton(onClick = { navigateToSignUp() }) {
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Login(
            navigateToSignUp = {},
            navigateToForgotPassword = {},
            navigateToHome = {}
        )
    }
}