package com.tpanh.jobfinder.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tpanh.jobfinder.model.ApplicationStatus
import com.tpanh.jobfinder.utils.normalizeString

@Composable
fun ChoosingItem(
    selected: Boolean,
    status: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else Color.Transparent,
            contentColor = if (selected) Color.White else MaterialTheme.colorScheme.onPrimaryContainer
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Text(text = normalizeString(status))
    }
    Spacer(modifier = Modifier.width(8.dp))
}