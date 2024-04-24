package com.tpanh.jobfinder.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.component.BoldTextComponent
import com.tpanh.jobfinder.component.ButtonComponent
import com.tpanh.jobfinder.component.ClickableRegisterComponent
import com.tpanh.jobfinder.component.DivideTextComponent
import com.tpanh.jobfinder.component.ErrorTextComponent
import com.tpanh.jobfinder.component.MyPasswordTextField
import com.tpanh.jobfinder.component.MyTextFieldWithIcon
import com.tpanh.jobfinder.component.NormalTextComponent
import com.tpanh.jobfinder.navigation.NavigationDestination
import com.tpanh.jobfinder.viewmodel.AppViewModelProvider
import com.tpanh.jobfinder.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

object SignInDestination : NavigationDestination {
    override val route: String = "sign_in"

    override val titleRes: Int = R.string.sign_in

}


@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    loginViewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            NormalTextComponent(value = stringResource(id = R.string.hey_there))

            BoldTextComponent(value = stringResource(id = R.string.welcome_back))

            if (loginUiState.loginError) {
                ErrorTextComponent(value = "Wrong password or wrong email")
            }

            MyTextFieldWithIcon(
                value = loginUiState.email,
                label = stringResource(id = R.string.email),
                imageVector = Icons.Filled.Email,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onStringValueChange = { loginViewModel.updateEmail(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyPasswordTextField(
                password = loginUiState.password,
                label = stringResource(id = R.string.password),
                imageVector = Icons.Filled.Lock,
                passwordVisible = loginViewModel.checkVisible,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onPasswordChange = { loginViewModel.updatePassword(it) },
                onPasswordVisibleChange = { loginViewModel.updateCheckVisible() }
            )


            Spacer(modifier = Modifier.weight(1f))
            ButtonComponent(value = "Login",
                isEnable = loginViewModel.validateInput(),
                onClick = {
                    coroutineScope.launch {
                        loginViewModel.login(navigateToHome)
                    }
                })


            DivideTextComponent()

            ClickableRegisterComponent(onTextSelected = { navigateToSignUp() })
        }
    }
}


@Preview
@Composable
fun DefaultSignInScreen() {
    SignInScreen(navigateToSignUp = {}, navigateToHome = {})
}
