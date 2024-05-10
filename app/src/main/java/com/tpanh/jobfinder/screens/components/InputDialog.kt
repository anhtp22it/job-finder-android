package com.tpanh.jobfinder.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputDialog(
    title: String,
    value: String,
    onDismissRequest: () -> Unit,
    onConfirm: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var tempValue by remember(value) { mutableStateOf(value) }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.height(1.dp), color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tempValue,
                    onValueChange = {
                        tempValue = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 12.sp
                    )
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(tempValue) }) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismissRequest() }) {
                Text(text = "Cancel")
            }
        },
    )
}

@Composable
fun NumberInputDialog(
    title: String,
    value: Int,
    onDismissRequest: () -> Unit,
    onConfirm: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var tempValue by remember(value) { mutableStateOf(value.toString()) }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.height(1.dp), color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tempValue,
                    onValueChange = {
                        tempValue = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 12.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val intValue = if (tempValue.isBlank()) 0 else tempValue.toInt()
                onConfirm(intValue)
            }) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismissRequest() }) {
                Text(text = "Cancel")
            }
        },
    )
}

@Composable
fun ListInputDialog(
    title: String,
    initialList: List<String>,
    onDismissRequest: () -> Unit,
    onConfirm: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var tempValue by remember { mutableStateOf("") }
    var tempList by remember { mutableStateOf(initialList.toMutableList()) }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.height(1.dp), color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                tempList.forEachIndexed { index, item ->
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.7f),
                        )
                        Button(
                            onClick = {
                                tempList = tempList.toMutableList().apply { removeAt(index) }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Red.copy(alpha = 0.5f)
                            )
                        ) {
                            Icon(
                                Icons.Filled.DeleteForever,
                                contentDescription = "Delete requirement"
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = tempValue,
                    onValueChange = { tempValue = it },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { tempList.add(tempValue); tempValue = "" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add requirement"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Add to list")
                }
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(tempList) }) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismissRequest() }) {
                Text(text = "Cancel")
            }
        },
    )
}

@Preview
@Composable
fun InputDialogPreview() {
    ListInputDialog(
        title = "Title",
        initialList = listOf("Hỗ trợ tư vấn chuẩn bị hồ sơ xin VISA (sau khi đậu tuyển dụng)", "Item 2"),
        onDismissRequest = {},
        onConfirm = {}
    )
}