package com.tpanh.jobfinder.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.extensions.dashedBorder
import com.tpanh.jobfinder.screens.components.NavigateBackBar
import com.tpanh.jobfinder.utils.formatBytes
import com.tpanh.jobfinder.utils.getFileName
import com.tpanh.jobfinder.utils.getFileSize
import com.tpanh.jobfinder.viewmodel.AddResumeViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AddResumeContent(
    addResumeViewModel: AddResumeViewModel = viewModel()
) {
    val uiState by addResumeViewModel.uiState.collectAsState()

    val contentResolver = LocalContext.current.contentResolver

    var pdfUri by remember { mutableStateOf<Uri?>(null) }
    var pdfFileName by remember { mutableStateOf<String?>(null) }
    var pdfFileSize by remember { mutableStateOf<Long?>(null) }
    val getContent =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            pdfUri = uri
            pdfFileName = uri?.let { getFileName(it, contentResolver) }
            pdfFileSize = getFileSize(uri, contentResolver)
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Add Resume",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (pdfUri == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .dashedBorder(
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.2f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { getContent.launch("application/pdf") }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.upload_file),
                        contentDescription = "Upload CV/Resume",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Upload CV/Resume",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 14.sp
                    )
                }

            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .dashedBorder(
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.2f),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_pdf),
                            contentDescription = "PDF Icon",
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            pdfFileName?.let { fileName ->
                                Text(
                                    text = "$fileName",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }

                            pdfFileSize?.let { size ->
                                val format =
                                    SimpleDateFormat(
                                        "dd MMM yyyy 'at' hh:mm aa",
                                        Locale.getDefault()
                                    )
                                Text(
                                    text = "${formatBytes(size)} • ${format.format(System.currentTimeMillis())}",
                                    color = Color.Black.copy(0.4f),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { pdfUri = null }
                    ) {
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Remove file",
                            color = Color.Red,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Upload files in PDF format up to 5 MB. Just upload it once and you can use it in your next application.",
            color = Color.Black.copy(0.4f),
            fontSize = 12.sp,
        )
    }
}

@Composable
fun AddResume(
    onBackClick: () -> Unit,
    navigateToViewProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            NavigateBackBar (
                navigateBack = { onBackClick() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AddResumeContent()
        }
    }
}

@Preview
@Composable
fun PreviewAddResume() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AddResume(
            onBackClick = { },
            navigateToViewProfile = { }
        )
    }
}