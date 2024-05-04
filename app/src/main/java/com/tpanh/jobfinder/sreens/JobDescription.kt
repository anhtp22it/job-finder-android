package com.tpanh.jobfinder.sreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.screens.UploadCv
import com.tpanh.jobfinder.screens.UploadCvContent
import com.tpanh.jobfinder.screens.components.NavigateBackBar

@Composable
fun JobDescription(
    navigateBack: () -> Unit
) {
    Scaffold (
        topBar = {
            NavigateBackBar (
                navigateBack = { navigateBack() },
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            JobDescriptionContent()
        }
    }
}

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

@Composable
fun BulletPointText(text: String) {
    Text(
        "•   $text",
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}

@Composable
fun JobDescriptionContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = 25.dp)
                .zIndex(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFAFECFE))
                    .padding(16.dp),
                alignment = Alignment.Center,
            )
        }
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "UI/UX Designer",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text (
                        text = "Google",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                    Text (
                        text = "California",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                    Text(
                        text = "1 day ago",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 18.sp
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Job Description",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            ExpandableText(
                text = "Google's software engineers develop the next-generation technologies that change how billions of users connect, explore, and interact with information and one another. Our products need to handle information at massive scale, and extend well beyond web search. We're looking for engineers who bring fresh ideas from all areas, including information retrieval, distributed computing, large-scale system design, networking and data storage, security, artificial intelligence, natural language processing, UI design and mobile; the list goes on and is growing every day. As a software engineer, you will work on a specific project critical to Google’s needs with opportunities to switch teams and projects as you and our fast-paced business grow and evolve. We need our engineers to be versatile, display leadership qualities and be enthusiastic to take on new problems across the full-stack as we continue to push technology forward.",

            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Requirements",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            BulletPointText("Sed ut perspiciatis unde omnis iste natus error sit.")

            Spacer(modifier = Modifier.height(12.dp))
            BulletPointText("Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.")

            Spacer(modifier = Modifier.height(12.dp))
            BulletPointText("Experience with one or more general purpose programming languages.")

            Spacer(modifier = Modifier.height(12.dp))
            BulletPointText("Bachelor's degree in Computer Science, similar technical field of study or equivalent practical experience.")

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    "APPLY NOW",
                    letterSpacing = 2.sp,
                )
            }

        }


    }



}


@Preview
@Composable
fun JobDescriptionPreview() {
    Surface {
        JobDescription(
            navigateBack = {}
        )
    }
}

