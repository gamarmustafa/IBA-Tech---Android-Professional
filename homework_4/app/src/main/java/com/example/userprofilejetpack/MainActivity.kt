package com.example.userprofilejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userprofilejetpack.ui.theme.UserProfileJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserProfileJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MyUI()
                }
            }
        }
    }
}

@Composable
fun MyUI() {
    Column(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .padding(10.dp)
                .clickable(enabled = true) {},
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(id = R.string.icon_desc)
        )
        Text(
            text = "User Profile",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(50.dp))
        Card(
            modifier = Modifier
                .size(200.dp)
                .align(alignment = Alignment.CenterHorizontally),
            shape = CircleShape,
            elevation = 20.dp,
            border = BorderStroke(2.dp, color = Color.Black)
        ) {
            Image(
                painterResource(R.drawable.stanley_downwood),
                contentDescription = stringResource(id = R.string.image_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            modifier = Modifier
                .clip(CircleShape)
                .align(alignment = Alignment.CenterHorizontally)
                .size(width = 150.dp, height = 50.dp), onClick = { }
        ) {
            Text(text = "Edit Profile", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(15.dp))
        AddRow(text1 = "First Name", text2 = "Stanley")
        AddRow(text1 = "Last Name", text2 = "Donwood")
        AddRow(text1 = "Email", text2 = "s.donwood@mail.com")
        AddRow(text1 = "Phone", text2 = "202-555-0143")
        AddRow(text1 = "Gender", text2 = "Male")
    }
}

@Composable
fun AddRow(text1: String, text2: String) {
    Spacer(modifier = Modifier.height(15.dp))
    Row(modifier = Modifier.padding(start = 20.dp)) {
        Text(
            text = "$text1:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 20.dp),
            fontSize = 24.sp
        )
        Text(text = text2, color = Color.Gray, fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UserProfileJetpackTheme {
        MyUI()
    }
}