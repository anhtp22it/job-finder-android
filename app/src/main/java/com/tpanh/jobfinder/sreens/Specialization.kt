package com.tpanh.jobfinder.sreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.shape
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
import androidx.compose.material3.TextField
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
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
fun Specialization() {
    Scaffold (
        topBar = {
            SpecializationTop()
        }
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            SpecializationContent()
        }
    }
}


@Composable
fun SpecializationTop() {
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
fun SpecializationContent() {

    var query by remember { mutableStateOf("") }
    var isPressed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically


        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFFFA500)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = "Setting",
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Specialization",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp

        )


        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(width = 165.dp, height = 185.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isPressed = !isPressed }
                    )
                    .scale(if (isPressed) 1.1f else 1f)
            ) {
                Column() {
                    IconButton(onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color(0xFFFFFFE0))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.design),
                                contentDescription = "Design",
                                modifier = Modifier.size(50.dp),
                                tint = Color(0xFFFFA500)
                            )
                        }
                    }

                    Text(text = "Design",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "140 Jobs",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }


            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(width = 165.dp, height = 185.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isPressed = !isPressed }
                    )
                    .scale(if (isPressed) 1.1f else 1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color(0xFFFFFFE0))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_finance),
                                contentDescription = "Finance",
                                modifier = Modifier.size(50.dp),
                                tint = Color(0xFFFFA500)
                            )
                        }
                    }

                    Text(text = "Finance",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "250 Jobs",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

        }


        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(width = 165.dp, height = 185.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isPressed = !isPressed }
                    )
                    .scale(if (isPressed) 1.1f else 1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color(0xFFFFFFE0))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_education),
                                contentDescription = "Education",
                                modifier = Modifier.size(50.dp),
                                tint = Color(0xFFFFA500)
                            )
                        }
                    }

                    Text(text = "Education",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "120 Jobs",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }


            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(width = 165.dp, height = 185.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isPressed = !isPressed }
                    )
                    .scale(if (isPressed) 1.1f else 1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color(0xFFFFFFE0))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_restaurant),
                                contentDescription = "Restaurant",
                                modifier = Modifier.size(50.dp),
                                tint = Color(0xFFFFA500)
                            )
                        }
                    }

                    Text(text = "Restaurant",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "85 Jobs",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

        }


        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(width = 165.dp, height = 185.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isPressed = !isPressed }
                    )
                    .scale(if (isPressed) 1.1f else 1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color(0xFFFFFFE0))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_health),
                                contentDescription = "Health",
                                modifier = Modifier.size(50.dp),
                                tint = Color(0xFFFFA500)
                            )
                        }
                    }

                    Text(text = "Health",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "235 Jobs",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }


            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(width = 165.dp, height = 185.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isPressed = !isPressed }
                    )
                    .scale(if (isPressed) 1.1f else 1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color(0xFFFFFFE0))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_programmer),
                                contentDescription = "Design",
                                modifier = Modifier.size(50.dp),
                                tint = Color(0xFFFFA500)
                            )
                        }
                    }

                    Text(text = "Programmer",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "412 Jobs",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

        }



    }









}













@Preview
@Composable
fun SpecializationPreview() {
    Specialization()
}



