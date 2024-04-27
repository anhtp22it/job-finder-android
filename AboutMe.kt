package com.example.job.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.job.AddWorkScreen
import com.example.job.R

class AboutMe : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AboutMe1()
                }
            }
        }
    }
}
@Composable
fun AboutMe1(){
    Column(modifier = Modifier.padding(start=25.dp,top=20.dp)){
        icon()
        Spacer(modifier = Modifier.height(12.dp))
        aboutme2()
        Spacer(modifier = Modifier.height(12.dp))
        DemoTextField5()
        savebutton1()
    }
}

@Composable
fun aboutme2(){
    Text(text = stringResource(id = R.string.test_text5),
        color = Color.Blue,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
    )
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DemoTextField5(){
    var aboutmee by remember {

        mutableStateOf(value = "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .width(350.dp)
            .height(200.dp),
        value = aboutmee, onValueChange = { newValue -> aboutmee = newValue},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp),
        placeholder = { Text(text = "Tell me about you.")},
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )

}
@Composable
fun savebutton1(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(500.dp)
            .height(900.dp)
    ){
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = "SAVE",
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
            )

        }
    }
}
@Composable
fun icon(){
    Image(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JobTheme {
        AboutMe1()
    }
}