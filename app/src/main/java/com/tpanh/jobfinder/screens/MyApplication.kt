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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.tpanh.jobfinder.viewmodel.MyApplicationViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MyApplication(
    navigateBack: () -> Unit,
    navigateToSearchJob: () -> Unit,
    myApplicationViewModel: MyApplicationViewModel = viewModel()
) {
    Scaffold (
        topBar = {
            NavigateBackBar (
                navigateBack = navigateBack
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            MyApplicationContent(
                navigateToSearchJob = navigateToSearchJob
            )
        }
    }
}

@Composable
fun MyApplicationContent(
    navigateToSearchJob: () -> Unit,
) {
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

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your application",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(painter = painterResource(
                    id = R.drawable.ic_google),
                    contentDescription = "Job image",
                    modifier = Modifier
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "UI/UX Designer",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Google inc • California, USA",
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "•    Shipped on February 14, 2022 at 11:30 am",
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "•    Updated by recruiter 8 hours ago",
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Job details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "•    Senior designer",
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "•    Full time",
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "•    1-3 Years work experience",
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "Application details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "•    CV/Resume",
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(0.5f),
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

                                Row {
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
                                    Text(
                                        text = " • ",
                                        color = Color.Black.copy(0.4f)
                                    )
                                    Text(
                                        text = "14 Feb 2022 at 11:30 am",
                                        fontSize = 12.sp,
                                        color = Color.Black.copy(0.4f),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { navigateToSearchJob() }
        ) {
            Text(
                text = "APPLY FOR MORE JOBS",
                letterSpacing = 2.sp,
            )
        }
    }
}

@Preview
@Composable
fun PreviewMyApplication() {
    Surface {
        MyApplication(
            navigateBack = {},
            navigateToSearchJob = {}
        )
    }
}