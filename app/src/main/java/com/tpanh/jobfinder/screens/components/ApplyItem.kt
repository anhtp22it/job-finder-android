package com.tpanh.jobfinder.screens.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.extensions.dashedBorder
import com.tpanh.jobfinder.model.ApplicationStatus
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobApply
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.model.Workplace
import com.tpanh.jobfinder.utils.formatBytes
import com.tpanh.jobfinder.utils.normalizeString
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ApplyItem(
    apply: JobApply,
    onAccept: (ApplicationStatus) -> Unit,
    onReject: (ApplicationStatus) -> Unit,
    user: User
) {
    val openFile = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

        }
    }
    var expanded by remember { mutableStateOf(true) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = user.avatar,
                    contentDescription = "Job Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = user.fullName,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(
                            text = user.email,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (apply.status == ApplicationStatus.PENDING) {
                Row {
                    IconButton(
                        onClick = { onReject(ApplicationStatus.REJECT) },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.Red,
                            containerColor = Color.Red.copy(0.1f)
                        )
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "reject"
                        )
                    }
                    IconButton(
                        onClick = { onAccept(ApplicationStatus.ACCEPT) },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.Green,
                            containerColor = Color.Green.copy(0.1f)
                        )
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "accept"
                        )
                    }
                }
            } else {
                Text(
                    text = normalizeString(apply.status.name),
                    color = if (apply.status == ApplicationStatus.ACCEPT) Color.Green else Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
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
                .clickable {
                    val pdfUri = Uri.parse(apply.cv)
                    pdfUri?.let { uri ->
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = uri
                            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        }
                        openFile.launch(Intent.createChooser(intent, "Open file with"))
                    }
                }
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
                        Text(
                            text = "CV.pdf",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Row {
                            val format =
                                SimpleDateFormat(
                                    "dd MMM yyyy 'at' hh:mm aa",
                                    Locale.getDefault()
                                )
                            Text(
                                text = "${format.format(apply.updatedAt)}",
                                color = Color.Black.copy(0.4f),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ApplyItemPreview() {
    ApplyItem(
        apply = JobApply(
            id = "gHHHPjf6mNmSC0nRRQk4",
            userId = "6b7PerH7TJXiPtw5TyFi0WgT1G22",
            job = Job(
                id = "7m6d7h4hw5OAcTrDODpl",
                categoryId = "3dCfQTrtldyE3XUgxLLc",
                company = "VKU",
                createdAt = 1715314717068,
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                location = "Hanoi",
                title = "Software Engineer",
                image = "https://firebasestorage.googleapis.com/v0/b/jobfinder-4e0c0.appspot.com/o/jobs%2F5e87fc15-01b2-4766-acd5-8f6b74b47e49.jpg?alt=media&token=d75a0749-a874-4329-9e25-f64cb8512349",
                requirements = listOf(
                    "Hỗ trợ cơm trưa cho các lớp fulltime",
                    "Cho mượn trang thiết bị học tập trong quá trình đào tạo",
                    "Hỗ trợ thông dịch khi phỏng vấn cùng Doanh nghiệp",
                    "Hỗ trợ tư vấn chuẩn bị hồ sơ xin VISA (sau khi đậu tuyển dụng)"
                ),
                salary = 1500,
                subCategory = "Mobile Development",
                type = JobType.FULL_TIME,
                workplace = Workplace.ON_SITE
            ),
            status = ApplicationStatus.ACCEPT,
            updatedAt = 1716029611238
        ),
        onAccept = {},
        onReject = {},
        user = User(fullName = "Trần Phước Anh")
    )
}