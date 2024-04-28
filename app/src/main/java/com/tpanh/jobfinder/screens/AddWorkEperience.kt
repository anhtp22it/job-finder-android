package com.example.job

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun AddWorkScreen(){
    Column(modifier = Modifier.padding(start=25.dp,top=50.dp)) {
        GreetingText()
        Spacer(modifier = Modifier.height(20.dp))
        JobTitle()
        DemoTextField()
        Spacer(modifier = Modifier.height(12.dp))
        Company()
        DemoTextField1()
        Spacer(modifier = Modifier.height(12.dp))
        DateSection()
        DateSection1()
        Column (modifier = Modifier.padding(5.dp)){
            DemoCheckBox(title = "This is my position now")
        }
        Description1()
        Spacer(modifier = Modifier.height(12.dp))
        DemoTextField4()
        Spacer(modifier = Modifier.height(30.dp))
        savebutton()
    }
}

@Composable
fun GreetingText(){
    Text(text = "12345",
        color = Color.Blue,
        fontSize = 30.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
    )

}
@Composable
fun JobTitle(){
    Text(text = "12345",
        color = Color.Blue,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
    )
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DemoTextField(){
    var jobTitle1 by remember {

        mutableStateOf(value = "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
//    val containerColor = FilledTextFieldTokens.ContainerColor.toColor()
    TextField(
        modifier = Modifier
            .width(350.dp)
            .height(55.dp),
        value = jobTitle1, onValueChange = { newValue -> jobTitle1 = newValue},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp),
        trailingIcon = {
            IconButton(onClick = {
                jobTitle1=""
            }) {
             Icon(Icons.Default.Close, contentDescription = null )
            }
        },
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
fun Company(){
    Text(text = "12345",
        color = Color.Blue,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
    )
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DemoTextField1(){
    var company by remember {

        mutableStateOf(value = "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .width(350.dp)
            .height(55.dp),
        value = company, onValueChange = { newValue -> company = newValue},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp),
        trailingIcon = {
            IconButton(onClick = {
                company=""
            }) {
                Icon(Icons.Default.Close, contentDescription = null )
            }
        },
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
fun DateSection() {
    Row(modifier = Modifier.fillMaxWidth()) {
        StartDate()
        Spacer(modifier = Modifier.width(100.dp))
        EndDate()
    }
}

@Composable
fun DateSection1() {
    Row(modifier = Modifier.fillMaxWidth()) {
        DemoTextField2()
        Spacer(modifier = Modifier.width(30.dp))
        DemoTextField3()
    }
}
@Composable
fun StartDate(){
    Text(text = "12345",
        color = Color.Blue,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun DemoTextField2(){
    var startdate by remember {

        mutableStateOf(value = "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = Modifier
            .width(160.dp)
            .height(55.dp),
        value = startdate, onValueChange = { newValue -> startdate = newValue},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp),

        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )

}

@Composable
fun EndDate(){
    Text(
        text = "12345",
        color = Color.Blue,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
    )
}

@Composable
fun DemoTextField3(){
    var enddate by remember {

        mutableStateOf(value = "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = Modifier
            .width(160.dp)
            .height(55.dp),
        value = enddate, onValueChange = { newValue -> enddate = newValue},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp),

        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )

}
@Composable
fun DemoCheckBox(title: String){
    var ischecked by remember {
        mutableStateOf(false)
    }
    return Row() {
        Checkbox(checked = ischecked, onCheckedChange = {
            ischecked= it
        },
           colors = CheckboxDefaults.colors(
               checkedColor = Color.Blue,
               uncheckedColor = Color.Gray
           )

        )
    }
}
@Composable
fun Description1(){
    Text(
        text = "12345",
        color = Color.Blue,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun DemoTextField4(){
    var description1 by remember {

        mutableStateOf(value = "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .width(350.dp)
            .height(200.dp),
        value = description1, onValueChange = { newValue -> description1 = newValue},
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp),
        placeholder = { Text(text = "Write additional information here")},
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
fun savebutton(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(400.dp)
            .height(300.dp)
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
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Surface {
        AddWorkScreen()
    }
}