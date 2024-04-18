package com.tpanh.jobfinder.sreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tpanh.jobfinder.R
import com.tpanh.jobfinder.navigation.JobFinderNavigation
import com.tpanh.jobfinder.ui.theme.md_theme_light_background
import com.tpanh.jobfinder.viewmodel.AddEducationViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Filter() {
    Scaffold (
        topBar = {
            FilterTop()
        }
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            FilterContent()
        }
    }
}


@Composable
fun FilterTop() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled .ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@Composable
fun FilterContent() {
    var sliderPosition by remember { mutableStateOf(5f..50f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Filter",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 30.sp

        )
    }


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Category",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 14.dp)

        )
        Text(
            text = "Design",
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 44.dp, top = 34.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Sub Category",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 14.dp)

        )
        Text(
            text = "UX/UI Design",
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 44.dp, top = 34.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Location",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 14.dp)

        )
        Text(
            text = "Califonia",
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 44.dp, top = 34.dp)
        )
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Salary",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 14.dp)

        )
        Row() {
            Text(
                text = "Min: 5k",
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 44.dp, top = 34.dp)
            )
            Text(
                text = "Max: 50k",
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 205.dp, top = 34.dp)
            )
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            RangeSlider(
                value = sliderPosition,
                steps = 5,
                onValueChange = { range -> sliderPosition = range },
                valueRange = 5f..50f,
                onValueChangeFinished = {
                },
                modifier = Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = sliderPosition.toString())
        }
    }


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Divider(color = Color.Gray, thickness = 0.5.dp)

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Job Type",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 14.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},

            )
             {
                Text ("Full Time")
            }

            Button(onClick = {}) {
                Text ("Part Time")
            }

            Button(onClick = {}) {
                Text ("Remote")
            }
        }

    }

    Column(
        modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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
            Text("APPLY NOW",
                letterSpacing = 2.sp,
                )
        }
    }

}

@Preview
@Composable
fun FilterPreview() {
    Filter()
}



