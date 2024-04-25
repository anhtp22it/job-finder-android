package com.tpanh.jobfinder.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.tpanh.jobfinder.component.CheckboxComponent
import com.tpanh.jobfinder.component.ClickableLoginTextComponent
import com.tpanh.jobfinder.component.DivideTextComponent
import com.tpanh.jobfinder.component.MyPasswordTextField
import com.tpanh.jobfinder.component.MyTextFieldWithIcon
import com.tpanh.jobfinder.component.NormalTextComponent
import com.tpanh.jobfinder.navigation.NavigationDestination
import kotlinx.coroutines.launch

object SignUpDestination : NavigationDestination {
    override val route: String = "sign_up"
    override val titleRes: Int = R.string.sign_up
}

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,

    ) {

    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            NormalTextComponent(value = stringResource(id = R.string.hey_there))

            BoldTextComponent(value = stringResource(id = R.string.create_account))
            MyTextFieldWithIcon(
                value = "",
                label = stringResource(id = R.string.first_name),
                imageVector = Icons.Filled.AccountCircle,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onStringValueChange = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyTextFieldWithIcon(
                value = "",
                label = stringResource(id = R.string.last_name),
                imageVector = Icons.Filled.AccountCircle,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onStringValueChange = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyTextFieldWithIcon(
                value = "",
                label = stringResource(id = R.string.email),
                imageVector = Icons.Filled.Email,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onStringValueChange = {  }
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyPasswordTextField(
                password ="",
                label = stringResource(id = R.string.password),
                imageVector = Icons.Filled.Lock,
                passwordVisible = false,
                onPasswordChange = { },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onPasswordVisibleChange = {  }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                CheckboxComponent(
                    checkedState = false,
                    onCheckedChange = { },
                    privacyAndPolicy = "Remember me"
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Forgot Password?")
            }
//            Spacer(modifier = Modifier.height(12.dp))
//            CheckboxComponent(
//                checkedState = false,
//                onCheckedChange = { },
//                privacyAndPolicy = stringResource(
//                    id = R.string.term_and_condition
//                )
//            )
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(
                value = "Register",
                isEnable = true,
                onClick = {
                    coroutineScope.launch {

                    }
                })

            DivideTextComponent()

            ClickableLoginTextComponent(onTextSelected = { navigateToSignIn() })
        }
    }
}


@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
        SignUpScreen(navigateToSignIn = {})
}