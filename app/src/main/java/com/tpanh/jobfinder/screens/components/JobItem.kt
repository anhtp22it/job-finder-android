package com.tpanh.jobfinder.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.Workplace
import com.tpanh.jobfinder.utils.normalizeString

@Composable
fun JobItem(
    job: Job
) {
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
                    model = job.image,
                    contentDescription = "Job Image",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = job.title,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(
                            text = job.company,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "•",
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = job.location,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Icon(
                    Icons.Outlined.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "$${job.salary}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(text = "/Mo")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = job.subCategory,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = normalizeString(job.type.name),
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = normalizeString(job.workplace?.name ?: ""),
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun JobItemPreview() {
    JobItem(
        job = Job(
            id = "7m6d7h4hw5OAcTrDODpl",
            title = "Android Developer",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            location = "DN",
            company = "VKU",
            image = "https://firebasestorage.googleapis.com/v0/b/jobfinder-4e0c0.appspot.com/o/jobs%2F5e87fc15-01b2-4766-acd5-8f6b74b47e49.jpg?alt=media&token=d75a0749-a874-4329-9e25-f64cb8512349",
            type = JobType.FULL_TIME,
            requirements = listOf(
                "Hỗ trợ cơm trưa cho các lớp fulltime",
                "Cho mượn trang thiết bị học tập trong quá trình đào tạo",
                "Hỗ trợ thông dịch khi phỏng vấn cùng Doanh nghiệp",
                "Hỗ trợ tư vấn chuẩn bị hồ sơ xin VISA (sau khi đậu tuyển dụng)"
            ),
            categoryId = "3dCfQTrtldyE3XUgxLLc",
            workplace = Workplace.ON_SITE,
            subCategory = "Mobile Development",
            salary = 1500,
            createdAt = 1715314717068,
            userId = "6b7PerH7TJXiPtw5TyFi0WgT1G22"
        )
    )
}