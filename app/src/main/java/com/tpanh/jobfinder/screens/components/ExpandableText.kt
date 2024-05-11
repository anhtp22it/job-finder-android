package com.tpanh.jobfinder.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandableText(text: String, maxLength: Int = 150) {
    val readMore = remember { mutableStateOf(false) }

    val displayText = if (readMore.value) {
        text
    } else {
        text.take(maxLength) + if (text.length > maxLength) "..." else ""
    }

    Column {
        Text(
            displayText,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            lineHeight = 22.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (text.length > maxLength) {
            TextButton(
                onClick = { readMore.value = !readMore.value },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                content = {
                    Text(if (readMore.value) "Read Less" else "Read More")
                },
                shape = RoundedCornerShape(5.dp),
            )
        }
    }
}