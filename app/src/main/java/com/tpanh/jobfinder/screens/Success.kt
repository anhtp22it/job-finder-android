package com.tpanh.jobfinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.viewmodel.ForgotPasswordViewModel

@Composable
fun Success(
    navigateToLogin: () -> Unit = { },
    forgotPasswordViewModel: ForgotPasswordViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Successfully",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your password has been updated, please change your password regularly to avoid this happening",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(modifier = Modifier.height(72.dp))
        Image(
            painter = painterResource(id = R.drawable.success),
            contentDescription = "success",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { forgotPasswordViewModel.openYourEmail() }
        ) {
            Text(
                text = "CONTINUE",
                letterSpacing = 2.sp,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { navigateToLogin() }
        ) {
            Text(
                text = "BACK TO LOGIN",
                letterSpacing = 2.sp,
            )
        }
    }
}

@Preview
@Composable
fun SuccessPreview() {
    Surface {
        Success()
    }
}